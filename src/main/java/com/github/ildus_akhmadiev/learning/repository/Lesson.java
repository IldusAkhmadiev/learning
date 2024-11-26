package com.github.ildus_akhmadiev.learning.repository;

import java.util.List;

public interface Lesson {
    List<String> getQuestions();
    List<String> getAnswers();
}
