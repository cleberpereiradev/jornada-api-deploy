package com.jornada.api.dto.destinos;

import com.jornada.api.entity.enums.Estacoes;

public record DadosDetalhamentoDestino(Long id, String nome, String descricaoCompleta, Estacoes estacaoRecomendada) {

    public DadosDetalhamentoDestino(DadosAtualizacaoDestino dados) {
        this(dados.id(), dados.nome(), dados.descricaoCompleta(), dados.estacaoRecomendada());
    }

}
