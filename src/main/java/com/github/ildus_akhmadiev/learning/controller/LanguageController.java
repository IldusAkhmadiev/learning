package com.github.ildus_akhmadiev.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LanguageController {

    // Обрабатываем запрос для английского языка
    @GetMapping("/learn/eng")
    public String learnEnglish() {
        // Здесь возвращается имя шаблона, например "learn_english.html"
        return "learn_english";
    }
    @GetMapping("/learn/eng/practice1")
    public String practice1() {

        return "test_eng_1";
    }
}