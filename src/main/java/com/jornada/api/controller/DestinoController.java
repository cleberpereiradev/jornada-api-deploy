package com.jornada.api.controller;

import com.jornada.api.dto.destinos.*;
import com.jornada.api.entity.Destino;
import com.jornada.api.service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @GetMapping
    public ResponseEntity<List<DadosListagemDestino>> findAll() {
        var destinos = this.destinoService.findAll();
        return ResponseEntity.ok(destinos);
    }
    @GetMapping(value = "/{id}")
    public DadosListagemDestinoCompleto findById(@PathVariable Long id) {
        return destinoService.findById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody DadosCadastroDestino dados, UriComponentsBuilder uriBuilder) {
        var destino = new Destino(dados);
        this.destinoService.save(destino);
        var uri = uriBuilder.path("/destinos/{id}").buildAndExpand(destino.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemDestinoCompleto(destino));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        this.destinoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody DadosAtualizacaoDestino dados) {
        this.destinoService.update(dados);
        return ResponseEntity.ok(new DadosDetalhamentoDestino(dados));
    }

    @GetMapping(value = "/pesquisar")
    public List<DadosListagemDestino> findDestinationByName (@RequestParam("nome") String nome) {
        var destinos = destinoService.searchByNome(nome);

        return destinos;
    }

    @PostMapping(value = "/roteiro")
    public String gerarRoteiro(@RequestParam("nomeDestino") String nomeDestino, @RequestParam("quantidadeDias") int quantidadeDias) {
        return destinoService.gerarRoteiro(nomeDestino, quantidadeDias);
    }

    @PostMapping(value = "/roteiro-alt")
    public String gerarRoteiroAlt(@RequestParam("nomeDestino") String nomeDestino, @RequestParam("quantidadeDias") int quantidadeDias) {
        return destinoService.gerarRoteiroAlt(nomeDestino, quantidadeDias);
    }

    @GetMapping(value = "/estacao")
    public ResponseEntity<List<DadosListagemDestino>> findByEstacao() {
        return ResponseEntity.ok(destinoService.findByEstacao());
    }

    @GetMapping(value = "/destinos-home")
    public ResponseEntity<List<DadosListagemDestinoAleatorio>> findRandomDestinos() {
        return ResponseEntity.ok(destinoService.findRandomDestinos());
    }

}
