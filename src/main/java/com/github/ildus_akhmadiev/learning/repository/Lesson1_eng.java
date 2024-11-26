package com.github.ildus_akhmadiev.learning.repository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class Lesson1_eng implements Lesson{
    public List<String> getQuestions() {
        return new ArrayList<>(Arrays.asList("I","You","We","He","She","It"
        ));
    }
    public List<String> getAnswers() {
        return new ArrayList<>(Arrays.asList("Я","Ты","Мы","Он","Она","Это"
        ));
    }
}
