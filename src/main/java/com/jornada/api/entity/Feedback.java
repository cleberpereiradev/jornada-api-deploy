package com.jornada.api.entity;

import com.jornada.api.dto.feedbacks.DadosAtualizacaoFeedback;
import com.jornada.api.dto.feedbacks.DadosCadastroFeedback;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "Feedback")
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeUsuario;

    @NotNull
    private String feedback;
    @NotNull
    private Long nota;

    public Feedback(DadosCadastroFeedback dados) {
        this.id = dados.id();
        this.nomeUsuario = dados.nomeUsuario();
        this.feedback = dados.feedback();
        this.nota = dados.nota();
    }

    public void atualizarFeedback(DadosAtualizacaoFeedback dados) {
        if(dados.nomeUsuario() != null) {
            this.nomeUsuario = dados.nomeUsuario();
        }
        if(dados.feedback() != null) {
            this.feedback = dados.feedback();
        }
        if(dados.nota() != null) {
            this.nota = dados.nota();
        }
    }
}
