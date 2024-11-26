package com.github.ildus_akhmadiev.learning.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "practice_lessons")
@Getter
@Setter
@NoArgsConstructor
public class PracticeLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lesson_type")
    private String lessonType; // например, "PRONOUN", "VERB", "NOUN"

    @Column(name = "question")
    private String question;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "incorrect_answers")
    private String incorrectAnswers; // можно хранить как JSON или comma-separated

    @Column(name = "correct_feedback")
    private String correctFeedback;

    @Column(name = "incorrect_feedback")
    private String incorrectFeedback;

    // Геттеры и сеттеры
    // Конструкторы
}