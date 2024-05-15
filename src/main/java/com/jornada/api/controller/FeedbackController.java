package com.jornada.api.controller;

import com.jornada.api.dto.feedbacks.DadosAtualizacaoFeedback;
import com.jornada.api.dto.feedbacks.DadosCadastroFeedback;
import com.jornada.api.dto.feedbacks.DadosListagemFeedback;
import com.jornada.api.entity.Feedback;
import com.jornada.api.repository.FeedbackRepository;
import com.jornada.api.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @PostMapping
    @Transactional
    public ResponseEntity saveFeedback(@RequestBody DadosCadastroFeedback dados, UriComponentsBuilder uriComponentsBuilder) {

        Feedback feedback = new Feedback(dados);
        feedbackRepository.save(feedback);

        var uri = uriComponentsBuilder.path("/feedbacks/{id}").buildAndExpand(feedback.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemFeedback(feedback));

    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody DadosAtualizacaoFeedback dados) {
        this.feedbackService.update(dados);
        return ResponseEntity.ok(new DadosListagemFeedback(dados));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.feedbackService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemFeedback>> findAll() {
        var list = this.feedbackService.findAll();

        return ResponseEntity.ok(list);
    }
    @GetMapping(value = "feedbacks-home")
    public List<DadosListagemFeedback> listarDepoimentosRandom() {
        return this.feedbackService.findRandomFeedbacks();
    }

}
