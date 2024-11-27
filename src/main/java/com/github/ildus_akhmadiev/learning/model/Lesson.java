package com.github.ildus_akhmadiev.learning.model;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String language;
    private int lessonNumber;
    private String title;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Question> questions;

    // Getters and setters
}
