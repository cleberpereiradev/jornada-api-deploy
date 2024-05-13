package com.jornada.api.dto.destinos;

import com.jornada.api.entity.Destino;
import com.jornada.api.entity.enums.Estacoes;

import java.math.BigDecimal;

public record DadosListagemDestinoCompleto(Long id, String nome, String descricaoCompleta, String estacaoRecomendada) {

    public DadosListagemDestinoCompleto (Destino destino) {
        this(
                destino.getId(),
                destino.getNome(),
                destino.getDescricaoCompleta(),
                destino.getEstacaoRecomendada().getDescricao()
        );
    }
}
