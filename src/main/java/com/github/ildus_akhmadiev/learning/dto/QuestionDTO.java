package com.github.ildus_akhmadiev.learning.dto;

import java.util.List;

public class QuestionDTO {
    private String pronoun;
    private String correctAnswer;
    private List<String> options;

    public QuestionDTO(String pronoun, String correctAnswer, List<String> options) {
        this.pronoun = pronoun;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }
}
