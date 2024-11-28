package com.github.ildus_akhmadiev.learning.dto;

import java.time.LocalDateTime;

public class UserLessonResultDTO {
    private String userId;
    private Long lessonId;
    private Double resultPercent;
    private LocalDateTime completedAt;

    // Геттеры и сеттеры
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Double getResultPercent() {
        return resultPercent;
    }

    public void setResultPercent(Double resultPercent) {
        this.resultPercent = resultPercent;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
