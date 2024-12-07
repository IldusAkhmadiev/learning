package com.github.ildus_akhmadiev.learning.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

@Service
public class SpeechService {



    public String getText(MultipartFile file) {
        // Реализация обработки аудио файла и конвертация его в текст
        // Например, можно использовать библиотеку для распознавания речи
        String text = "Преобразованный текст";
        // Это просто пример, нужно реализовать реальную логику
        return text; }
}
