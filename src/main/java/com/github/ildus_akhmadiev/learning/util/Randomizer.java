package com.github.ildus_akhmadiev.learning.util;

import com.github.ildus_akhmadiev.learning.repository.Lesson1_eng;

import java.util.Collections;
import java.util.List;


public class Randomizer {

    private Lesson1_eng lesson;

    public Randomizer(Lesson1_eng lesson) {
        this.lesson = lesson;
    }

    public List<String> getRandomizedQuestions() {
        List<String> questions = lesson.getQuestions();
        Collections.shuffle(questions);
        return questions;
    }

    public List<String> getRandomizedAnswers() {
        List<String> answers = lesson.getAnswers();
        Collections.shuffle(answers);
        return answers;
    }
}
