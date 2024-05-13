package com.jornada.api.entity;

import com.jornada.api.dto.depoimentos.DadosAtualizacaoDepoimento;
import com.jornada.api.dto.depoimentos.DadosCadastroDepoimento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Depoimento")
@Table(name = "depoimentos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Depoimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    @Column(columnDefinition = "TEXT", name = "depoimento")
    private String textoDepoimento;
    @NotNull
    private String local;

    public Depoimento(DadosCadastroDepoimento dados) {
        this.nome = dados.nome();
        this.textoDepoimento = dados.textoDepoimento();
        this.local = dados.local();
    }


    public void atualizarDepoimento(DadosAtualizacaoDepoimento dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.textoDepoimento() != null) {
            this.textoDepoimento = dados.textoDepoimento();
        }
        if(dados.local() != null) {
            this.local = dados.local();
        }
    }
}
