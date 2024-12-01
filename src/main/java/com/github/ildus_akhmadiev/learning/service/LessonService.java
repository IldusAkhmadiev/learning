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

    @Autowired
    private QuizService quizService; // Добавлен сервис для генерации ответов

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
    public Answer getCorrectAnswerByQuestion(String question) {
        return answerRepository.findByText(question);
    }
    public Answer getTextByQuestionId(Integer questionId) {
        return answerRepository.getTextByQuestionId(questionId);
    }



    public List<QuestionWithAnswersDTO> getQuestionsWithAnswersByLessonId(Integer lessonId, int totalOptions) {
        // Получаем все вопросы для урока
        List<Question> questions = questionRepository.findByLessonId(lessonId);

        // Создаем список DTO
        List<QuestionWithAnswersDTO> dtos = new ArrayList<>();

        for (Question question : questions) {
            // Получаем правильный ответ для вопроса
            Answer correctAnswer = getCorrectAnswerByQuestionId(question.getId());

            if (correctAnswer == null) {
                throw new RuntimeException("Correct answer not found for question ID: " + question.getId());
            }

            // Генерируем варианты ответов
            List<String> options = quizService.generateOptions(correctAnswer.getText(), totalOptions);

            // Создаем DTO
            QuestionWithAnswersDTO dto = new QuestionWithAnswersDTO(
                    question.getId(),
                    question.getText(),
                    options
            );

            // Добавляем DTO в список
            dtos.add(dto);
        }

        return dtos;
    }
}
