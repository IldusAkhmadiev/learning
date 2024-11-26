package com.github.ildus_akhmadiev.learning.dto;

public class LessonAnswerDTO {
    private String pronoun;
    private String answer;

    // Конструктор по умолчанию
    public LessonAnswerDTO() {}

    // Конструктор с параметрами
    public LessonAnswerDTO(String pronoun, String answer) {
        this.pronoun = pronoun;
        this.answer = answer;
    }

    // Геттеры и сеттеры
    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}