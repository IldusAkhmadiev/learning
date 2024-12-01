package com.github.ildus_akhmadiev.learning.service;

import com.github.ildus_akhmadiev.learning.enums.PartOfSpeech;
import com.github.ildus_akhmadiev.learning.model.*;
import com.github.ildus_akhmadiev.learning.repository.AnswerRepository;
import com.github.ildus_akhmadiev.learning.repository.LessonRepository;
import com.github.ildus_akhmadiev.learning.repository.QuestionRepository;
import com.github.ildus_akhmadiev.learning.repository.WordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public void populateQuestionsFromWords(Integer lessonId) {
        // Получаем урок по его номеру
        Lesson lesson = lessonRepository.getLessonByLessonNumber(lessonId);

        // Извлекаем слова с partOfSpeech = PRONOUN
        List<Word> pronouns = wordRepository.findByPartOfSpeech(PartOfSpeech.PRONOUN);

        for (Word word : pronouns) {
            // Создаём новый вопрос
            Question question = new Question();
            question.setText(word.getOriginal()); // Устанавливаем текст вопроса
            question.setQuestionType("PRONOUN"); // Указываем тип
            question.setLesson(lesson); // Привязываем к уроку

            // Создаём ответы на основе переводов
            List<Answer> answers = new ArrayList<>();
            for (Translation translation : word.getTranslations()) {
                if ("ru".equals(translation.getLanguage())) {
                    Answer answer = new Answer();
                    answer.setText(translation.getTranslation()); // Устанавливаем текст перевода
                    answer.setCorrect(true); // Указываем, что ответ правильный
                    answer.setQuestion(question); // Связываем с вопросом
                    answers.add(answer);
                }
            }

            // Привязываем ответы к вопросу
            question.setAnswers(answers);

            // Сохраняем вопрос и связанные ответы
            questionRepository.save(question);
        }
    }
}