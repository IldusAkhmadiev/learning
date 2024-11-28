package com.github.ildus_akhmadiev.learning.service;

import com.github.ildus_akhmadiev.learning.dto.QuestionWithAnswersDTO;
import com.github.ildus_akhmadiev.learning.model.Answer;
import com.github.ildus_akhmadiev.learning.model.Lesson;
import com.github.ildus_akhmadiev.learning.model.Question;
import com.github.ildus_akhmadiev.learning.repository.AnswerRepository;
import com.github.ildus_akhmadiev.learning.repository.LessonRepository;
import com.github.ildus_akhmadiev.learning.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public Lesson getLessonById(Integer lessonId) {
        return lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    public List<Question> getQuestionsByLessonId(Integer lessonId) {
        return questionRepository.findByLessonId(lessonId);
    }

    public List<Answer> getAnswersByQuestionId(Integer questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public Answer getCorrectAnswerByQuestionId(Integer questionId) {
        return answerRepository.findByQuestionIdAndCorrectIsTrue(questionId);
    }
    public Question findByQuestionText(String questionText) {
        return questionRepository.findByText(questionText);
    }
    public Integer getQuestionIdByQuestionText(String questionText) {
        return questionRepository.getQuestionIdByText(questionText);
    }
    public Answer getAnswerByQuestionIdAndText(Integer questionId, String text) {
        return answerRepository.getAnswerByQuestionIdAndText(questionId, text);
    }

    public List<QuestionWithAnswersDTO> getQuestionsWithAnswersByLessonId(Integer lessonId) {
        // Получаем все вопросы для урока
        List<Question> questions = questionRepository.findByLessonId(lessonId);

        // Создаем список DTO
        List<QuestionWithAnswersDTO> dtos = new ArrayList<>();

        for (Question question : questions) {
            // Получаем ответы для каждого вопроса
            List<Answer> answers = answerRepository.findByQuestionId(question.getId());

            // Извлекаем текст ответов
            List<String> answerTexts = answers.stream()
                    .map(Answer::getText)
                    .toList();

            // Создаем DTO
            QuestionWithAnswersDTO dto = new QuestionWithAnswersDTO(
                    question.getId(),
                    question.getText(),
                    answerTexts
            );

            // Добавляем DTO в список
            dtos.add(dto);
        }

        return dtos;
    }

}

