package com.github.ildus_akhmadiev.learning.integration.dictionaryapidev;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/api/v1/public/translate/{word}")
    public ResponseEntity<UniversalResponse> getDefinition(@PathVariable String word) {
        Mono<UniversalResponse> response = dictionaryApiDevService.getWordDefinitionAsync(word);
        UniversalResponse universalResponse = response.block();
        if(universalResponse.errorResponse != null) {
            return ResponseEntity.status(404).body(universalResponse);
        } else {
            return ResponseEntity.status(200).body(universalResponse);
        }
    }
}