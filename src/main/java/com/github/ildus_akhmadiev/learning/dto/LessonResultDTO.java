package com.github.ildus_akhmadiev.learning.dto;

public class LessonResultDTO {
    private boolean correct;
    private String feedback;

    // Конструктор по умолчанию
    public LessonResultDTO() {}

    // Конструктор с параметрами
    public LessonResultDTO(boolean correct, String feedback) {
        this.correct = correct;
        this.feedback = feedback;
    }

    // Геттеры и сеттеры
    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}