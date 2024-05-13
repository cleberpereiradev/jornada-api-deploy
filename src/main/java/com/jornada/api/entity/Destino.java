package com.jornada.api.entity;

import com.jornada.api.dto.destinos.DadosAtualizacaoDestino;
import com.jornada.api.dto.destinos.DadosCadastroDestino;
import com.jornada.api.entity.enums.Estacoes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity(name = "Destino")
@Table(name = "destinos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;

    private String descricaoCompleta;

    private Estacoes estacaoRecomendada;

    public Destino(DadosCadastroDestino dados) {
        this.nome = dados.nome();
        this.descricaoCompleta = dados.descricaoCompleta();
        this.estacaoRecomendada = dados.estacaoRecomendada();
    }

    public void atualizarDestino(DadosAtualizacaoDestino dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.descricaoCompleta() != null) {
            this.descricaoCompleta = dados.descricaoCompleta();
        }
        if(dados.estacaoRecomendada() != null) {
            this.estacaoRecomendada = dados.estacaoRecomendada();
        }
    }

}
