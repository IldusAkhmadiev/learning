package com.github.ildus_akhmadiev.learning.controller;

import com.github.ildus_akhmadiev.learning.dto.QuestionWithAnswersDTO;
import com.github.ildus_akhmadiev.learning.model.Question;
import com.github.ildus_akhmadiev.learning.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class LanguageController {

    @Autowired
    private final LessonService lessonService;

    public LanguageController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    // Обрабатываем запрос для английского языка
    @GetMapping("/learn/eng")
    public String learnEnglish() {
        // Здесь возвращается имя шаблона, например "learn_english.html"
        return "learn_english";
    }
    // Путь для практики с динамическим идентификатором урока
    @GetMapping("/learn/eng/practice/{lessonId}")
    public String practice(@PathVariable Integer lessonId, Model model) {
        // Вернем страницу для этого урока, например: test_eng_1.html, test_eng_2.html и т.д.
        model.addAttribute("lessonId", lessonId); // передаем lessonId в модель
        List<QuestionWithAnswersDTO> questionsWithAnswersByLessonId = lessonService.getQuestionsWithAnswersByLessonId(lessonId);
        model.addAttribute("questionsWithAnswersByLessonId", questionsWithAnswersByLessonId);
        return "test_eng_" + lessonId;
    }


}