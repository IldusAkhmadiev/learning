package com.github.ildus_akhmadiev.learning.dto;

public class LessonResultSummaryDTO {
    private Long lessonId;
    private Double bestResultPercent;
    private Long attemptCount;

    public LessonResultSummaryDTO(Long lessonId, Double bestResultPercent, Long attemptCount) {
        this.lessonId = lessonId;
        this.bestResultPercent = bestResultPercent;
        this.attemptCount = attemptCount;
    }

    // Getters and setters
    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Double getBestResultPercent() {
        return bestResultPercent;
    }

    public void setBestResultPercent(Double bestResultPercent) {
        this.bestResultPercent = bestResultPercent;
    }

    public Long getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(Long attemptCount) {
        this.attemptCount = attemptCount;
    }
}
