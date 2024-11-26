package com.github.ildus_akhmadiev.learning.service;


import com.github.ildus_akhmadiev.learning.model.PracticeLesson;
import com.github.ildus_akhmadiev.learning.repository.PracticeLessonRepository;
import org.springframework.stereotype.Service;

@Service
public class PracticeServiceStrategy {
    private final PracticeLessonRepository lessonRepository;

    public PracticeServiceStrategy(PracticeLessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    // Метод для получения урока по ID
    public PracticeLesson getLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    // Метод проверки ответа
    public boolean checkAnswer(Long lessonId, String userAnswer) {
        PracticeLesson lesson = getLessonById(lessonId);
        return lesson.getCorrectAnswer().equals(userAnswer);
    }

    // Метод генерации фидбэка
    public String generateFeedback(Long lessonId, boolean isCorrect) {
        PracticeLesson lesson = getLessonById(lessonId);
        return isCorrect
                ? lesson.getCorrectFeedback()
                : lesson.getIncorrectFeedback();
    }
}