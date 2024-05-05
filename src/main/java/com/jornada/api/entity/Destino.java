package com.jornada.api.entity;

import com.jornada.api.dto.destinos.DadosAtualizacaoDestino;
import com.jornada.api.dto.destinos.DadosCadastroDestino;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "Destino")
@Table(name = "destinos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    private String destinoImgUrl;
    private String destinoImgUrl2;
    @NotNull
    @Column(length = 160)
    private String metaDescricao;
    private String descricaoCompleta;
    @NotNull
    private BigDecimal preco;

    public Destino(DadosCadastroDestino dados) {
        this.nome = dados.nome();
        this.destinoImgUrl = dados.destinoImgUrl();
        this.destinoImgUrl2 = dados.destinoImgUrl2();
        this.metaDescricao = dados.metaDescricao();
        this.descricaoCompleta = dados.descricaoCompleta();
        this.preco = dados.preco();
    }

    public void atualizarDestino(DadosAtualizacaoDestino dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.destinoImgUrl() != null) {
            this.destinoImgUrl = dados.destinoImgUrl();
        }
        if(dados.destinoImgUrl2() != null) {
            this.destinoImgUrl = dados.destinoImgUrl2();
        }
        if(dados.metaDescricao() != null) {
            this.metaDescricao = dados.metaDescricao();
        }
        if(dados.descricaoCompleta() != null) {
            this.descricaoCompleta = dados.descricaoCompleta();
        }
        if(dados.preco() != null) {
            this.preco = dados.preco();
        }
    }

}
