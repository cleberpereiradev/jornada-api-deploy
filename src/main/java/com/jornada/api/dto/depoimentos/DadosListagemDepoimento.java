package com.jornada.api.dto.depoimentos;

import com.jornada.api.entity.Depoimento;

import java.util.Optional;

public record DadosListagemDepoimento(Long id, String nome, String textoDepoimento, String imgUrl) {
    public DadosListagemDepoimento(Depoimento depoimento) {
        this(depoimento.getId(), depoimento.getNome(),depoimento.getTextoDepoimento(),depoimento.getImgUrl());
    }

    public DadosListagemDepoimento(DadosListagemDepoimento dadosListagemDepoimento) {
        this(dadosListagemDepoimento.id(), dadosListagemDepoimento.nome, dadosListagemDepoimento.textoDepoimento, dadosListagemDepoimento.imgUrl);
    }

}
