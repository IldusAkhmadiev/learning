package com.github.ildus_akhmadiev.learning.rest;

import com.github.ildus_akhmadiev.learning.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class SpeechRestController {

    @Autowired
    SpeechService speechService;


    @PostMapping("/api/sound")
    public String uploadAudio(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        File tempFile = null; // Временный файл для сохранения аудио
        String uniqueResultFile = null; // Уникальный файл для результата
        try {
            // Создаем временный файл
            tempFile = File.createTempFile("audio", ".wav");
            file.transferTo(tempFile);

            // Генерируем уникальное имя для выходного JSON файла
            uniqueResultFile = Paths.get("src/main/resources/result_" + UUID.randomUUID() + ".json")
                    .toAbsolutePath().toString();

            // Относительный путь к Python-скрипту
            String pythonScriptPath = Paths.get("src/main/java/com/github/ildus_akhmadiev/learning/utils/speech.py")
                    .toAbsolutePath().toString();

            // Создаем ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder(
                    "python", pythonScriptPath,
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