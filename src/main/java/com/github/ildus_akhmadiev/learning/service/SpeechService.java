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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Service
public class SpeechService {

    public String getText(MultipartFile file,String otherPythonScriptPath) {
        if(otherPythonScriptPath==null) {
            // Относительный путь к Python-скрипту
            otherPythonScriptPath = Paths.get("src/main/java/com/github/ildus_akhmadiev/learning/utils/speech.py")
                    .toAbsolutePath().toString();
        }
        File tempFile = null; // Временный файл для сохранения аудио
        String uniqueResultFile = null; // Уникальный файл для результата
        try {
            // Создаем временный файл
            tempFile = File.createTempFile("audio", ".wav");
            file.transferTo(tempFile);

            // Генерируем уникальное имя для выходного JSON файла
            uniqueResultFile = Paths.get("src/main/resources/result_" + UUID.randomUUID() + ".json")
                    .toAbsolutePath().toString();


            // Создаем ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder(
                    "python", otherPythonScriptPath,
                    uniqueResultFile,
                    tempFile.getAbsolutePath()
            );

            pb.redirectErrorStream(true); // Перенаправляем ошибки в стандартный вывод
            Process process = pb.start();

            // Чтение вывода Python-скрипта
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            // Чтение ошибок
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line);
            }

            // Логирование вывода и ошибок
            System.out.println("Python output: " + output.toString());
            System.out.println("Python error: " + errorOutput.toString());

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                String result = new String(Files.readAllBytes(new File(uniqueResultFile).toPath()));
                return result;
            } else {
                return "Ошибка при обработке файла в Python. Вывод ошибки: " + errorOutput.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Произошла ошибка при обработке файла.";
        } finally {
            // Удаление временного файла
            if (tempFile != null && tempFile.exists()) {
                boolean deleted = tempFile.delete();
                if (!deleted) {
                    System.err.println("Не удалось удалить временный файл: " + tempFile.getAbsolutePath());
                }
            }
            // Удаляем уникальный файл результата
            if (uniqueResultFile != null) {
                File resultFile = new File(uniqueResultFile);
                if (resultFile.exists() && !resultFile.delete()) {
                    System.err.println("Не удалось удалить файл результата: " + uniqueResultFile);
                }
            }
        }
      }
}
