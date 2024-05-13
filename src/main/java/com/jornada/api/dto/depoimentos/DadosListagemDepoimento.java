package com.jornada.api.dto.depoimentos;

import com.jornada.api.entity.Depoimento;

public record DadosListagemDepoimento(Long id, String nome, String textoDepoimento, String local) {
    public DadosListagemDepoimento(Depoimento depoimento) {
        this(depoimento.getId(), depoimento.getNome(),depoimento.getTextoDepoimento(),depoimento.getLocal());
    }

    public DadosListagemDepoimento(DadosListagemDepoimento dadosListagemDepoimento) {
        this(dadosListagemDepoimento.id(), dadosListagemDepoimento.nome, dadosListagemDepoimento.textoDepoimento, dadosListagemDepoimento.local);
    }

}
