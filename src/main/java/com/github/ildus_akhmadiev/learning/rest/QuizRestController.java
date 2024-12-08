package com.github.ildus_akhmadiev.learning.rest;

import com.github.ildus_akhmadiev.learning.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
public class QuizRestController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/generate")
    public ResponseEntity<List<String>> generateQuiz(
            @RequestParam String correctAnswer,
            @RequestParam int totalOptions
    ) {
        List<String> options = quizService.generateOptions(correctAnswer, totalOptions);
        return ResponseEntity.ok(options);
    }
}