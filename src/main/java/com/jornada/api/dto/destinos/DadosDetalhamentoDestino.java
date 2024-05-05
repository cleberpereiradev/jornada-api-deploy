package com.jornada.api.dto.destinos;

import java.math.BigDecimal;

public record DadosDetalhamentoDestino(Long id, String nome, String destinoImgUrl, String destinoImgUrl2, String metaDescricao, String descricaoCompleta, BigDecimal preco) {

    public DadosDetalhamentoDestino(DadosAtualizacaoDestino dados) {
        this(dados.id(), dados.nome(), dados.destinoImgUrl(), dados.destinoImgUrl2(), dados.metaDescricao(), dados.descricaoCompleta(), dados.preco());
    }

}
