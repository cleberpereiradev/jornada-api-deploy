package com.jornada.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jornada.api.dto.destinos.*;
import com.jornada.api.entity.Destino;
import com.jornada.api.entity.enums.Estacoes;
import com.jornada.api.gemini.GeminiService;
import com.jornada.api.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    @Autowired
    GeminiService geminiService;

    public List<DadosListagemDestino> findAll() {
        var destinos = this.destinoRepository.findAll();
        return destinos.stream().map(DadosListagemDestino::new).toList();
    }

    public DadosListagemDestinoCompleto findById(Long id) {
        var destino = this.destinoRepository.findById(id).get();
        return new DadosListagemDestinoCompleto(destino);
    }

    public void save(Destino destino) {
        if(destino.getDescricaoCompleta() == null){
            String formatacao = "Formatação da descrição= parágrafo simples, sem quebras de linha. Exemplo: \"Cidade localizada no sul do Brasil, com clima tropical e muitas praias.\", máximo 400 caracter";
            String promptDescricao = "Digite a descrição completa da cidade: " + destino.getNome() + ". " + formatacao;
            destino.setDescricaoCompleta(geminiService.getCompletion(promptDescricao));
        }
        this.destinoRepository.save(destino);
    }

    public String gerarRoteiro(String nomeDestino, int quantidadeDias) {
            String formatacao = "Resposta dividida em tópicos para cada dia, com sugestão de passeios pela cidade e região. Exemplo: \"Dia 1: Visita ao museu da cidade e almoço em restaurante local. Dia 2: Passeio de barco pela baía e jantar em restaurante típico.\". Máximo 1000 caracteres.;";
            String promptRoteiro = "Roteiro de viagem para a cidade: " + nomeDestino + " para uma viagem de:  " + quantidadeDias + " dias. " + formatacao;
        return geminiService.getCompletion(promptRoteiro);
    }

    public String gerarRoteiroAlt(String nomeDestino, int quantidadeDias) {
        String formatacao = "Resposta dividida em tópicos para cada dia, com sugestão de passeios pela cidade e região focado em custo-benefício, incluindo acampamentos ou hostels. Exemplo: \"Dia 1: Visita ao museu da cidade e almoço em restaurante local. Dia 2: Passeio de barco pela baía e jantar em restaurante típico.\". Máximo 1000 caracteres.;";
        String promptRoteiro = "Roteiro de viagem para a cidade: " + nomeDestino + " para uma viagem de:  " + quantidadeDias + " dias. " + formatacao;
        return geminiService.getCompletion(promptRoteiro);
    }

    public void deleteById(Long id) {
        if(this.destinoRepository.existsById(id)){
            this.destinoRepository.deleteById(id);
        }
    }

    public void update(@RequestBody DadosAtualizacaoDestino dados) {
        var destino = destinoRepository.getReferenceById(dados.id());
        destino.atualizarDestino(dados);
    }


    public DadosCadastroDestino gerarDestino(String nomeDestino) throws JsonProcessingException {
        if (destinoRepository.existsByNome(nomeDestino)) {
            throw new RuntimeException("Destino já existe");
        }
        String formatacao = "Criar Json com as propriedades: nome, descrição completa(aproximadamente 400 caracteres) e estação recomendada. Exemplo: {\"nome\": \"Florianópolis\", \"descricaoCompleta\": \"Cidade localizada no sul do Brasil, com clima tropical e muitas praias.\", \"estacaoRecomendada\": \"VERAO\"}";
        String promptDestino = "Criar um destino com o nome: " + nomeDestino + formatacao;
        String destinoJson = geminiService.getCompletion(promptDestino);

        ObjectMapper objectMapper = new ObjectMapper();
        DadosCadastroDestino dadosCadastroDestino = objectMapper.readValue(destinoJson, DadosCadastroDestino.class);

        return dadosCadastroDestino;
    }

    public List<DadosListagemDestino> searchByNome(String nome) throws JsonProcessingException {
        var destinos = destinoRepository.findAll();
        List<Destino> destinosEncontrados = new ArrayList<>();
        for(Destino destino : destinos) {
            if (destino.getNome().toLowerCase().contains(nome.toLowerCase())){
                var destinoEncontrado = destinoRepository.findById(destino.getId()).get();
                destinosEncontrados.add(destinoEncontrado);
            }
        }
        if(destinosEncontrados.isEmpty()) {
            // Cria um novo destino se nenhum foi encontrado
            DadosCadastroDestino destinoGerado = this.gerarDestino(nome);
            Destino novoDestino = new Destino(destinoGerado);
            this.save(novoDestino);
            destinosEncontrados.add(novoDestino);
        }
        return destinosEncontrados.stream().map(DadosListagemDestino::new).toList();
    }


    public List<DadosListagemDestino> findByEstacao() {
        Estacoes estacaoAtual = Estacoes.getEstacaoAtual();
        var destinosPorEstacao = destinoRepository.findByEstacaoRecomendada(estacaoAtual);
        return destinosPorEstacao.stream().map(DadosListagemDestino::new).toList();
    }

    public List<DadosListagemDestinoAleatorio> findRandomDestinos() {
        var destinos = this.destinoRepository.findRandomDestinos();
        if(destinos.isEmpty()) {
            throw new RuntimeException("Nenhum destino encontrado");
        }
        return destinos;
    }
}
