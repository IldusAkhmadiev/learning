package com.github.ildus_akhmadiev.learning.rest;

import com.github.ildus_akhmadiev.learning.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/ask")
    public String askOpenAI(@RequestBody String prompt) {
        try {
            return openAIService.getChatCompletion(prompt);
        } catch (IOException e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}