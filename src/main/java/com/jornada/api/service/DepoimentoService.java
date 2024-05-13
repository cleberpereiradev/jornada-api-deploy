package com.jornada.api.service;

import com.jornada.api.dto.depoimentos.DadosAtualizacaoDepoimento;
import com.jornada.api.dto.depoimentos.DadosDetalhamentoDepoimento;
import com.jornada.api.dto.depoimentos.DadosListagemDepoimento;
import com.jornada.api.entity.Depoimento;
import com.jornada.api.infra.exception.ValidacaoException;
import com.jornada.api.repository.DepoimentoRepository;
import com.jornada.api.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

@Service
public class DepoimentoService {
    @Autowired
    private DepoimentoRepository repository;


    public List<DadosListagemDepoimento> findAll() {
        return this.repository.findAll().stream().map(DadosListagemDepoimento::new).toList();
    }

    public DadosDetalhamentoDepoimento findById(Long id) {
        var depoimento = repository.getReferenceById(id);
        return new DadosDetalhamentoDepoimento(depoimento);

    }

    public void save(Depoimento dados) {
        repository.save(dados);
    }

    public void deleteById(Long id) {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
        }else {
            throw new RuntimeException("ID Não encontrado!");
        }

    }

    public void update(@RequestBody DadosAtualizacaoDepoimento dados){
            var depoimento = repository.getReferenceById(dados.id());
            depoimento.atualizarDepoimento(dados);
    }

    public List<DadosListagemDepoimento> findRandomDepoimentos() {
        var depoimentosAleatorios = repository.findRandomDepoimentos();
        if(depoimentosAleatorios.isEmpty()){
            throw new ValidacaoException("Não há depoimentos para mostrar!");
        }
        return depoimentosAleatorios;
    }

    public List<DadosListagemDepoimento> findDepoimentosByDestino(String nome) {
        var depoimentos = repository.findDepoimentosByLocal(nome);
        if(depoimentos == null){
            throw new ValidacaoException("Não há depoimentos para mostrar!");
        }
        return Collections.singletonList(depoimentos);
    }

}
