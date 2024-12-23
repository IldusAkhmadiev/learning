package com.github.ildus_akhmadiev.learning.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class SpeechRestController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${flask.server.url}")
    private String flaskApiUrl;

    public SpeechRestController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/api/v1/public/sound")
    public ResponseEntity<String> uploadAudio(@RequestParam("file") MultipartFile file) throws IOException {
        // Создаем MultiValueMap для multipart/form-data
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        // Создаем ByteArrayResource из содержимого файла
        ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        // Добавляем файл в body
        body.add("file", fileResource);

        // Настраиваем заголовки
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Создаем HTTP entity
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            // Отправляем POST запрос
            ResponseEntity<String> response = restTemplate.exchange(
                    flaskApiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // Проверяем ответ
            if (response.getStatusCode() == HttpStatus.OK) {
                JsonNode jsonResponse = objectMapper.readTree(response.getBody());
                return ResponseEntity.ok(jsonResponse.toString());
            } else {
                return ResponseEntity.status(response.getStatusCode())
                        .body("{\"error\": \"Failed to process audio file\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

}
