package com.github.ildus_akhmadiev.learning.integration.dictionaryapidev;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class DictionaryRestController {

    private final DictionaryApiDevService dictionaryApiDevService;

    public DictionaryRestController(DictionaryApiDevService dictionaryApiDevService) {
        this.dictionaryApiDevService = dictionaryApiDevService;
    }

    @GetMapping("/define")
    public ResponseEntity<UniversalResponse> getDefinition(@RequestParam String word) {
        Mono<UniversalResponse> response = dictionaryApiDevService.getWordDefinitionAsync(word);
        UniversalResponse universalResponse = response.block();
        if(universalResponse.errorResponse != null) {
            System.out.println("Ошибка");
            System.out.println(universalResponse.errorResponse + " " + universalResponse.wordResponse);
            return ResponseEntity.status(404).body(universalResponse);
        } else {
            System.out.println("Верно");
            System.out.println(universalResponse.errorResponse + " " + universalResponse.wordResponse);
            return ResponseEntity.status(200).body(universalResponse);
        }
    }
}