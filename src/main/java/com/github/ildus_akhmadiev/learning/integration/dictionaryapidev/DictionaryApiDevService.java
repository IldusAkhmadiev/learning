package com.github.ildus_akhmadiev.learning.integration.dictionaryapidev;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class DictionaryApiDevService {
    private final WebClient webClient;

    public DictionaryApiDevService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en").build();
    }

    public Mono<UniversalResponse> getWordDefinitionAsync(String word) {
        return webClient.get()
                .uri("/{word}", word)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<WordResponse>>() {})
                .map(wordResponses -> {
                    // Если запрос успешен, возвращаем объект UniversalResponse с успешным ответом
                    return new UniversalResponse(null, wordResponses); // Считаем, что возвращается один WordResponse
                })
                .onErrorResume(throwable -> {
                    // Если возникла ошибка, возвращаем UniversalResponse с ошибкой
                    ErrorResponse errorResponse = new ErrorResponse(throwable.getMessage());
                    return Mono.just(new UniversalResponse(errorResponse, null));
                });
    }
}