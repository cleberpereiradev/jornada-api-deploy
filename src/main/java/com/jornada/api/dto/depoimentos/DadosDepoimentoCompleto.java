package com.jornada.api.dto.depoimentos;

import com.jornada.api.entity.Depoimento;

public record DadosDepoimentoCompleto(Long id, String nome,String textoDepoimento,String local) {
    public DadosDepoimentoCompleto(Depoimento depoimento) {
        this(depoimento.getId(),depoimento.getNome(),depoimento.getTextoDepoimento(),depoimento.getLocal());
    }

}
