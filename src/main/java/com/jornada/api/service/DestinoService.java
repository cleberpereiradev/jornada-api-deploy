package com.jornada.api.service;

import com.jornada.api.dto.destinos.DadosAtualizacaoDestino;
import com.jornada.api.dto.destinos.DadosListagemDestino;
import com.jornada.api.dto.destinos.DadosListagemDestinoCompleto;
import com.jornada.api.entity.Destino;
import com.jornada.api.entity.enums.Estacoes;
import com.jornada.api.gemini.GeminiInterface;
import com.jornada.api.gemini.GeminiService;
import com.jornada.api.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Month;
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

    public List<DadosListagemDestino> searchByNome(String nome){
        var destinos = destinoRepository.findAll();
        List<Destino> destinosEncontrados = new ArrayList<>();
        for(Destino destino : destinos) {
            if (destino.getNome().toLowerCase().contains(nome.toLowerCase())){
                var destinoEncontrado = destinoRepository.findById(destino.getId()).get();
                destinosEncontrados.add(destinoEncontrado);
            }
        }
        if(destinosEncontrados.isEmpty()) {
            throw new RuntimeException("Nenhum destino encontrado");
        }
        return destinosEncontrados.stream().map(DadosListagemDestino::new).toList();
    }

    public List<DadosListagemDestino> findByEstacao() {
        Estacoes estacaoAtual = Estacoes.getEstacaoAtual();
        var destinosPorEstacao = destinoRepository.findByEstacaoRecomendada(estacaoAtual);
        return destinosPorEstacao.stream().map(DadosListagemDestino::new).toList();
    }
}
