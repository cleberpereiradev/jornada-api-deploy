package com.jornada.api.dto.feedbacks;

import com.jornada.api.entity.Feedback;

public record DadosListagemFeedback(Long id, String nomeUsuario, String feedback, Long nota) {

    public DadosListagemFeedback(Feedback feedback) {
        this(feedback.getId(), feedback.getNomeUsuario(), feedback.getFeedback(), feedback.getNota());
    }

    public DadosListagemFeedback(DadosAtualizacaoFeedback dados) {
        this(dados.id(), dados.nomeUsuario(), dados.feedback(), dados.nota());
    }


}
