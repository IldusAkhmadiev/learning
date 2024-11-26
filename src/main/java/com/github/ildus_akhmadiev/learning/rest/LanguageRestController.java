package com.github.ildus_akhmadiev.learning.rest;

import com.github.ildus_akhmadiev.learning.dto.AnswerDTO;
import com.github.ildus_akhmadiev.learning.dto.LessonAnswerDTO;
import com.github.ildus_akhmadiev.learning.dto.LessonResultDTO;
import com.github.ildus_akhmadiev.learning.dto.PronounAnswerDTO;
import com.github.ildus_akhmadiev.learning.service.PracticeService;
import com.github.ildus_akhmadiev.learning.service.PracticeServiceStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/learn/eng/practice")
public class LanguageRestController {

    private final PracticeServiceStrategy practiceServiceStrategy;
    private final PracticeService practiceService;

    // Конструктор для внедрения зависимостей
    public LanguageRestController(PracticeService practiceService, PracticeServiceStrategy practiceServiceStrategy) {
        this.practiceService = practiceService;
        this.practiceServiceStrategy = practiceServiceStrategy;
    }

    @PostMapping("/submit")
    public ResponseEntity<LessonResultDTO> submitAnswer(
            @RequestBody LessonAnswerDTO answerDTO
    ) {
        // Для текущей реализации используем старый метод
        boolean isCorrect = practiceService.checkPronounTranslation(
                answerDTO.getQuestion(),
                answerDTO.getAnswer()
        );

        // Получаем обратную связь
        String feedback = practiceService.getAnswerFeedback(
                answerDTO.getQuestion(),
                isCorrect
        );

        // Возвращаем ответ
        return ResponseEntity.ok(new LessonResultDTO(isCorrect, feedback));
    }

    // Метод для работы с уроками из БД (будущая реализация)
    @PostMapping("/{lessonId}/submit")
    public ResponseEntity<LessonResultDTO> submitLessonAnswer(
            @PathVariable Long lessonId,
            @RequestBody LessonAnswerDTO answerDTO
    ) {
//        boolean isCorrect = practiceService.checkLessonAnswer(lessonId, answerDTO.getAnswer());
//
//        String feedback = practiceService.generateLessonFeedback(lessonId, isCorrect);

//        return ResponseEntity.ok(new LessonResultDTO(isCorrect, feedback));
        return ResponseEntity.ok(new LessonResultDTO(true,"Просто верно"));
    }
}