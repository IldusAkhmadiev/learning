package com.github.ildus_akhmadiev.learning.rest;

import com.github.ildus_akhmadiev.learning.dto.LessonAnswerDTO;
import com.github.ildus_akhmadiev.learning.dto.LessonResultDTO;
import com.github.ildus_akhmadiev.learning.dto.UserLessonResultDTO;
import com.github.ildus_akhmadiev.learning.model.Answer;
import com.github.ildus_akhmadiev.learning.model.UserLessonResult;
import com.github.ildus_akhmadiev.learning.service.LessonService;

import com.github.ildus_akhmadiev.learning.service.UserLessonResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/learn/eng/practice")
public class LanguageRestController {


    @Autowired
    private final LessonService lessonService;
    @Autowired
    private UserLessonResultService userLessonResultService;

    // Конструктор для внедрения зависимостей
    public LanguageRestController(LessonService lessonService) {
        this.lessonService = lessonService;

    }

    @PostMapping("/{lessonId}/submit")
    public ResponseEntity<LessonResultDTO> submitLessonAnswer(
            @PathVariable Long lessonId,
            @RequestBody LessonAnswerDTO answerDTO
    ) {
        // Проверяем, существует ли ответ
        Answer answer = lessonService.getTextByQuestionId(answerDTO.getQuestionId());

        if (answer.getText().equals(answerDTO.getAnswer())) {
            return ResponseEntity.ok(new LessonResultDTO(true,"Верно" ));
        }
        return ResponseEntity.ok(new LessonResultDTO(false, "Неверно"));
    }

    @PostMapping("/results")
    public ResponseEntity<UserLessonResult> saveUserLessonResult(@RequestBody UserLessonResultDTO userLessonResultDTO) {
        UserLessonResult userLessonResult = userLessonResultService.saveUserLessonResult(userLessonResultDTO);
        return ResponseEntity.ok(userLessonResult);
    }

}