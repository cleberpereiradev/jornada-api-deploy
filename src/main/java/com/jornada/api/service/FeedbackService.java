package com.jornada.api.service;


import com.jornada.api.dto.feedbacks.DadosAtualizacaoFeedback;
import com.jornada.api.dto.feedbacks.DadosListagemFeedback;
import com.jornada.api.infra.exception.ValidacaoException;
import com.jornada.api.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<DadosListagemFeedback> findAll() {
        return feedbackRepository.findAll().stream().map(DadosListagemFeedback::new).toList();
    }


    public DadosListagemFeedback findById(Long id) {
        var feedback = feedbackRepository.getReferenceById(id);
        return new DadosListagemFeedback(feedback);
    }

    public void update(DadosAtualizacaoFeedback dados) {
        var feedback = feedbackRepository.getReferenceById(dados.id());
        feedback.atualizarFeedback(dados);
    }

    public void deleteById(Long id) {
        if(this.feedbackRepository.existsById(id)){
            this.feedbackRepository.deleteById(id);
        }else {
            throw new RuntimeException("ID Não encontrado!");
        }
    }

    public List<DadosListagemFeedback> findRandomFeedbacks() {
        var feedbacks = feedbackRepository.findRandomFeedbacks();
        if(feedbacks.isEmpty()){
            throw new ValidacaoException("Não há feedbacks para mostrar!");
        }
        return feedbacks;
    }
}
