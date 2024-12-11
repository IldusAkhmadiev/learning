package com.github.ildus_akhmadiev.learning.dto;

public class LessonAnswerDTO {
    private Integer questionId;
    private String question;
    private String answer;
    private String userId;

    // Конструктор по умолчанию
    public LessonAnswerDTO() {}

    public LessonAnswerDTO(Integer questionId, String question, String answer, String userId) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setLessonId(Integer question_id) {
        this.questionId = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}