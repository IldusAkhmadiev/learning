package com.github.ildus_akhmadiev.learning.dto;

public class LessonAnswerDTO {
    private Integer questionId;
    private String question;
    private String answer;

    // Конструктор по умолчанию
    public LessonAnswerDTO() {}

    // Конструктор с параметрами
    public LessonAnswerDTO(Integer question_id,String question, String answer) {
        this.questionId = question_id;
        this.question = question;
        this.answer = answer;
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