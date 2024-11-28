package com.github.ildus_akhmadiev.learning.rest;

import com.github.ildus_akhmadiev.learning.dto.LessonAnswerDTO;
import com.github.ildus_akhmadiev.learning.dto.LessonResultDTO;
import com.github.ildus_akhmadiev.learning.model.Answer;
import com.github.ildus_akhmadiev.learning.service.LessonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/learn/eng/practice")
public class LanguageRestController {




    @Autowired
    private final LessonService lessonService;

    // Конструктор для внедрения зависимостей
    public LanguageRestController(LessonService lessonService) {
        this.lessonService = lessonService;

    }

    // Метод для работы с уроками из БД (будущая реализация)
    @PostMapping("/{lessonId}/submit")
    public ResponseEntity<LessonResultDTO> submitLessonAnswer(
            @PathVariable Long lessonId,
            @RequestBody LessonAnswerDTO answerDTO
    ) {
        Answer answer = lessonService.getAnswerByQuestionIdAndText(
                Integer.valueOf(answerDTO.getLessonId()), answerDTO.getAnswer());
        if(answer == null) {
            ResponseEntity.ok(new LessonResultDTO(false,"Отсутствует ответ"));
        }

        return ResponseEntity.ok(new LessonResultDTO(answer.isCorrect(),answer.getFeedback()));
    }
}