package com.github.ildus_akhmadiev.learning.service;


import com.github.ildus_akhmadiev.learning.model.PracticeLesson;
import com.github.ildus_akhmadiev.learning.repository.Lesson1_eng;
import com.github.ildus_akhmadiev.learning.repository.PracticeLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PracticeService {

    @Autowired
    private final Lesson1_eng lesson1_eng;
    public List<String> getQuestions() {
        return lesson1_eng.getQuestions();

    }
    public List<String> getAnswers() {
        return lesson1_eng.getAnswers();

    }

    // Временное хранение уроков в памяти (пока не настроили полностью БД)
    private static final Map<String, String> CORRECT_TRANSLATIONS = new HashMap<>() {{
        put("I", "я");
        put("You", "ты");
        put("He/She/It", "он");
    }};

    private final PracticeLessonRepository lessonRepository;

    public PracticeService(Lesson1_eng lesson1Eng, PracticeLessonRepository lessonRepository) {
        lesson1_eng = lesson1Eng;
        this.lessonRepository = lessonRepository;
    }

    // Метод для совместимости со старым подходом
    public boolean checkPronounTranslation(String pronoun, String answer) {
        return CORRECT_TRANSLATIONS.get(pronoun).equals(answer);
    }

    // Метод для совместимости со старым подходом
    public String getAnswerFeedback(String pronoun, boolean isCorrect) {
        if (isCorrect) {
            return String.format("Верно! '%s' переводится как '%s'.",
                    pronoun, CORRECT_TRANSLATIONS.get(pronoun));
        } else {
            return String.format("Неверно. '%s' переводится как '%s'.",
                    pronoun, CORRECT_TRANSLATIONS.get(pronoun));
        }
    }

    // Новый метод для работы с БД (когда будет готова модель)
    public boolean checkLessonAnswer(Long lessonId, String answer) {
        PracticeLesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Урок не найден"));

        return lesson.getCorrectAnswer().equals(answer);
    }

    // Метод для генерации фидбэка из БД
    public String generateLessonFeedback(Long lessonId, boolean isCorrect) {
        PracticeLesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Урок не найден"));

        return isCorrect ? lesson.getCorrectFeedback() : lesson.getIncorrectFeedback();
    }
}