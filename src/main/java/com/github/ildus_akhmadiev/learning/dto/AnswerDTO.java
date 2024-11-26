package com.github.ildus_akhmadiev.learning.dto;

public class AnswerDTO {
    private String answer;

    // Конструктор по умолчанию
    public AnswerDTO() {}

    // Конструктор с параметром
    public AnswerDTO(String answer) {
        this.answer = answer;
    }

    // Геттер
    public String getAnswer() {
        return answer;
    }

    // Сеттер
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}