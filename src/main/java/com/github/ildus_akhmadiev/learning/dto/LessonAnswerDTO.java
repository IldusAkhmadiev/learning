package com.github.ildus_akhmadiev.learning.dto;

public class LessonAnswerDTO {
    private String lessonId;
    private String question;
    private String answer;

    // Конструктор по умолчанию
    public LessonAnswerDTO() {}

    // Конструктор с параметрами
    public LessonAnswerDTO(String lessonId,String question, String answer) {
        this.lessonId = lessonId;
        this.question = question;
        this.answer = answer;
    }


    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
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