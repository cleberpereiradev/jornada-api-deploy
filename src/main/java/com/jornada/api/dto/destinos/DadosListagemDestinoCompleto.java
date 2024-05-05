package com.jornada.api.dto.destinos;

import com.jornada.api.entity.Destino;

import java.math.BigDecimal;

public record DadosListagemDestinoCompleto(Long id, String nome, String destinoImgUrl, String destinoImgUrl2, String metaDescicao, String descricaoCompleta, BigDecimal preco) {

    public DadosListagemDestinoCompleto (Destino destino) {
        this(
                destino.getId(),
                destino.getNome(),
                destino.getDestinoImgUrl(),
                destino.getDestinoImgUrl2(),
                destino.getMetaDescricao(),
                destino.getDescricaoCompleta(),
                destino.getPreco()
        );
    }
}
