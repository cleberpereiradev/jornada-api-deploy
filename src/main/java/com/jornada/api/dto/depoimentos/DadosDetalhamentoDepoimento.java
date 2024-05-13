package com.jornada.api.dto.depoimentos;

import com.jornada.api.entity.Depoimento;

public record DadosDetalhamentoDepoimento(Long id, String nome, String textoDepoimento, String local) {

    public DadosDetalhamentoDepoimento(Depoimento depoimento) {
        this(depoimento.getId(), depoimento.getNome(), depoimento.getTextoDepoimento(), depoimento.getLocal());
    }

    public DadosDetalhamentoDepoimento(DadosAtualizacaoDepoimento dados) {
        this(dados.id(), dados.nome(), dados.textoDepoimento(), dados.local());
    }

}
