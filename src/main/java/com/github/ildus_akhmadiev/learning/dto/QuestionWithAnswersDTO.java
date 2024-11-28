package com.github.ildus_akhmadiev.learning.dto;

import java.util.List;

public class QuestionWithAnswersDTO {
    private Integer questionId;
    private String questionText;
    private List<String> answers;

    public QuestionWithAnswersDTO(Integer questionId, String questionText, List<String> answers) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.answers = answers;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
