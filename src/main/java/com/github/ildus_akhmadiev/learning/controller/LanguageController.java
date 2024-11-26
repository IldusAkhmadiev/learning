package com.github.ildus_akhmadiev.learning.controller;

import com.github.ildus_akhmadiev.learning.repository.Lesson1_eng;
import com.github.ildus_akhmadiev.learning.service.PracticeService;
import com.github.ildus_akhmadiev.learning.util.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LanguageController {

    @Autowired
    PracticeService practiceService;
    @Autowired
    private Lesson1_eng lesson1_eng;

    // Обрабатываем запрос для английского языка
    @GetMapping("/learn/eng")
    public String learnEnglish() {
        // Здесь возвращается имя шаблона, например "learn_english.html"
        return "learn_english";
    }
    // Путь для практики с динамическим идентификатором урока
    @GetMapping("/learn/eng/practice/{lessonId}")
    public String practice(@PathVariable String lessonId, Model model) {
        // Вернем страницу для этого урока, например: test_eng_1.html, test_eng_2.html и т.д.
        Randomizer randomizer = new Randomizer(lesson1_eng);
        model.addAttribute("lessonId", lessonId); // передаем lessonId в модель
        model.addAttribute("questions", randomizer.getRandomizedQuestions());
        model.addAttribute("answers", randomizer.getRandomizedAnswers());
        return "test_eng_" + lessonId;
    }


}