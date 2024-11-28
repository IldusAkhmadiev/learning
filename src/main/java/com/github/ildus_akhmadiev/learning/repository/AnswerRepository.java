package com.github.ildus_akhmadiev.learning.repository;

import com.github.ildus_akhmadiev.learning.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByQuestionId(Integer questionId);
    Answer findByQuestionIdAndCorrectIsTrue(Integer questionId);
    Answer getAnswerByQuestionIdAndText(Integer questionId, String text);

}
