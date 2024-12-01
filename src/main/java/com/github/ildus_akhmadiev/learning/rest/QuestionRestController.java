package com.github.ildus_akhmadiev.learning.rest;

import com.github.ildus_akhmadiev.learning.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class QuestionRestController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/populate/{id}")
    public ResponseEntity<String> populateQuestions(@PathVariable Integer id) {
        questionService.populateQuestionsFromWords(id);
        return ResponseEntity.ok("Questions populated successfully");
    }
}