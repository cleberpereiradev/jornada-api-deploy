package com.jornada.api.entity.enums;

import java.time.LocalDate;
import java.time.Month;

public enum Estacoes {
    PRIMAVERA(0, "Primavera"),
    VERAO(1, "Verão"),
    OUTONO(2, "Outono"),
    INVERNO(3, "Inverno");

    private final int index;
    private final String descricao;

    Estacoes(int index, String descricao) {
        this.index = index;
        this.descricao = descricao;
    }

    public int getIndex() {
        return index;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Estacoes getByIndex(int index) {
        for (Estacoes estacao : values()) {
            if (estacao.getIndex() == index) {
                return estacao;
            }
        }
        throw new IllegalArgumentException("Invalid Estacoes index: " + index);
    }

    public static Estacoes getEstacaoAtual() {
        LocalDate hoje = LocalDate.now();
        Month mes = hoje.getMonth();
        int dia = hoje.getDayOfMonth();

        if (mes == Month.MARCH && dia >= 21 || mes == Month.APRIL || mes == Month.MAY || mes == Month.JUNE && dia < 21) {
            return Estacoes.OUTONO;
        } else if (mes == Month.JUNE && dia >= 21 || mes == Month.JULY || mes == Month.AUGUST || mes == Month.SEPTEMBER && dia < 23) {
            return Estacoes.INVERNO;
        } else if (mes == Month.SEPTEMBER && dia >= 23 || mes == Month.OCTOBER || mes == Month.NOVEMBER || mes == Month.DECEMBER && dia < 21) {
            return Estacoes.PRIMAVERA;
        } else {
            return Estacoes.VERAO;
        }
    }
}