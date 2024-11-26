package com.github.ildus_akhmadiev.learning.dto;

public class PronounAnswerDTO {
    private String pronoun;
    private String answer;

    // Конструктор по умолчанию
    public PronounAnswerDTO() {}

    // Конструктор с параметрами
    public PronounAnswerDTO(String pronoun, String answer) {
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