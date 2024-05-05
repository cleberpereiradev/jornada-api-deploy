package com.jornada.api.controller;

import com.jornada.api.dto.depoimentos.*;
import com.jornada.api.entity.Depoimento;
import com.jornada.api.infra.exception.ValidacaoException;
import com.jornada.api.service.DepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoService service;

    @GetMapping
    public ResponseEntity<List<DadosListagemDepoimento>> findAll() {
        var list = this.service.findAll();

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    @Transactional
    public ResponseEntity findById(@PathVariable Long id) {
        var depoimento = this.service.findById(id);

        return ResponseEntity.ok(depoimento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody DadosCadastroDepoimento dados, UriComponentsBuilder uriBuilder) {
        var depoimento = new Depoimento(dados);
        this.service.save(depoimento);
        var uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(depoimento.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDepoimentoCompleto(depoimento));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody DadosAtualizacaoDepoimento dados) {
        this.service.update(dados);
        return ResponseEntity.ok(new DadosDetalhamentoDepoimento(dados));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "depoimentos-home")
    public List<DadosListagemDepoimento> listarDepoimentosRandom() {
        return this.service.findRandomDepoimentos();
    }

}
