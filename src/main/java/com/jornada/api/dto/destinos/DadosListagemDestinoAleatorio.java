package com.jornada.api.dto.destinos;

import com.jornada.api.entity.Destino;

public record DadosListagemDestinoAleatorio(Long id, String nome) {

    public DadosListagemDestinoAleatorio(Destino destino) {
        this(destino.getId(), destino.getNome());
    }
}
