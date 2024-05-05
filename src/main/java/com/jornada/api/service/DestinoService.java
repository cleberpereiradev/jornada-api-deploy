package com.jornada.api.service;

import com.jornada.api.dto.destinos.DadosAtualizacaoDestino;
import com.jornada.api.dto.destinos.DadosListagemDestino;
import com.jornada.api.dto.destinos.DadosListagemDestinoCompleto;
import com.jornada.api.entity.Destino;
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

    public List<DadosListagemDestino> findAll() {
        var destinos = this.destinoRepository.findAll();
        return destinos.stream().map(DadosListagemDestino::new).toList();
    }

    public DadosListagemDestinoCompleto findById(Long id) {
        var destino = this.destinoRepository.findById(id).get();
        return new DadosListagemDestinoCompleto(destino);
    }

    public void save(Destino destino) {
        this.destinoRepository.save(destino);
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
        if(destinosEncontrados.size() == 0) {
            throw new RuntimeException("Nenhum destino encontrado");
        }
        return destinosEncontrados.stream().map(DadosListagemDestino::new).toList();
    }
}
