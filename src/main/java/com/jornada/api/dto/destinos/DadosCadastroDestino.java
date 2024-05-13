package com.jornada.api.dto.destinos;

import com.jornada.api.entity.enums.Estacoes;

import java.math.BigDecimal;

public record DadosCadastroDestino(String nome, String descricaoCompleta, Estacoes estacaoRecomendada) {

}
