package com.jornada.api.repository;

import com.jornada.api.dto.feedbacks.DadosListagemFeedback;
import com.jornada.api.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("select f from Feedback f order by rand() limit 3")
    List<DadosListagemFeedback> findRandomFeedbacks();
}
