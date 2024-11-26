package com.github.ildus_akhmadiev.learning.rest;

import com.github.ildus_akhmadiev.learning.dto.AnswerDTO;
import com.github.ildus_akhmadiev.learning.dto.PronounAnswerDTO;
import com.github.ildus_akhmadiev.learning.service.PracticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/learn/eng/practice")
public class LanguageRestController {

    private final PracticeService practiceService;

    // Конструктор для внедрения зависимостей
    public LanguageRestController(PracticeService practiceService) {
        this.practiceService = practiceService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitAnswer(
            @RequestBody PronounAnswerDTO answerDTO
    ) {
        // Проверяем правильность ответа
        boolean isCorrect = practiceService.checkPronounTranslation(
                answerDTO.getPronoun(),
                answerDTO.getAnswer()
        );

        // Получаем обратную связь
        String feedback = practiceService.getAnswerFeedback(
                answerDTO.getPronoun(),
                isCorrect
        );

        // Возвращаем ответ
        return ResponseEntity.ok(Map.of(
                "correct", isCorrect,
                "feedback", feedback
        ));
    }
}