package com.github.ildus_akhmadiev.learning.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_lesson_results")
@Getter
@Setter
@NoArgsConstructor
public class UserLessonResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "lesson_id")
    private Long lessonId;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    // Геттеры и сеттеры

}