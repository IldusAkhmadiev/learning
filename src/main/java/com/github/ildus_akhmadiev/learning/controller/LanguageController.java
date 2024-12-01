package com.github.ildus_akhmadiev.learning.controller;

import com.github.ildus_akhmadiev.learning.dto.QuestionWithAnswersDTO;
import com.github.ildus_akhmadiev.learning.dto.UserLessonResultDTO;
import com.github.ildus_akhmadiev.learning.model.Question;
import com.github.ildus_akhmadiev.learning.model.UserLessonResult;
import com.github.ildus_akhmadiev.learning.service.LessonService;
import com.github.ildus_akhmadiev.learning.service.UserLessonResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/learn/eng/practice/{lessonId}")
    public String practice(
            @PathVariable Integer lessonId,
            @RequestParam(defaultValue = "7") int optionsCount, // Добавлен параметр запроса
            Model model,
            @AuthenticationPrincipal OAuth2User oAuth2User) {

        String userId = oAuth2User.getAttribute("sub"); // Уникальный идентификатор пользователя Google
        model.addAttribute("userId", userId);

        // Передаем lessonId в модель
        model.addAttribute("lessonId", lessonId);

        // Получаем вопросы с ответами, используя переданное количество вариантов
        List<QuestionWithAnswersDTO> questionsWithAnswersByLessonId =
                lessonService.getQuestionsWithAnswersByLessonId(lessonId, optionsCount);
        model.addAttribute("questionsWithAnswersByLessonId", questionsWithAnswersByLessonId);

        // Возвращаем страницу, например: test_eng_1.html, test_eng_2.html и т.д.
        return "test_eng_" + lessonId;
    }

    @GetMapping("/learn/eng/theory/{lessonId}")
    public String theory(
            @PathVariable Integer lessonId,
            Model model,
            @AuthenticationPrincipal OAuth2User oAuth2User) {

        String userId = oAuth2User.getAttribute("sub"); // Уникальный идентификатор пользователя Google
        model.addAttribute("userId", userId);

        // Передаем lessonId в модель
        model.addAttribute("lessonId", lessonId);

        // Возвращаем страницу теории, например: theory_eng_1.html, theory_eng_2.html и т.д.
        return "theory_eng_" + lessonId;
    }


}


