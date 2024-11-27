package com.github.ildus_akhmadiev.learning.model;


import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String text;
    private boolean correct;
    private String feedback;

    // Getters and setters
}
