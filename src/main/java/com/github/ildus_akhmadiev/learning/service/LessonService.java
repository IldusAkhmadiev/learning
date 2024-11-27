package com.github.ildus_akhmadiev.learning.service;

import com.github.ildus_akhmadiev.learning.model.Answer;
import com.github.ildus_akhmadiev.learning.model.Lesson;
import com.github.ildus_akhmadiev.learning.model.Question;
import com.github.ildus_akhmadiev.learning.repository.AnswerRepository;

import com.github.ildus_akhmadiev.learning.repository.LessonRepository;
import com.github.ildus_akhmadiev.learning.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;


    public List<Question> getQuestionsByLessonId(Integer lessonId) {
        return questionRepository.findByLessonId(lessonId);
    }

    public List<Answer> getAnswersByQuestionId(Integer questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public Answer getCorrectAnswerByQuestionId(Integer questionId) {
        return answerRepository.findByQuestionIdAndCorrectIsTrue(questionId);
    }
}
