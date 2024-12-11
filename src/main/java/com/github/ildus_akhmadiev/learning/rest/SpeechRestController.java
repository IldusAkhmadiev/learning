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


    @PostMapping("/api/v1/public/sound")
    public String uploadAudio(@RequestParam("file") MultipartFile file)  {
        return speechService.getText(file,null);
    }

    @PostMapping("/api/v1/public/sound/arabic")
    public String uploadAudioArabic(@RequestParam("file") MultipartFile file) {
        String pythonScriptPath = Paths.get("src/main/java/com/github/ildus_akhmadiev/learning/utils/speechArab.py")
                .toAbsolutePath().toString();
        return speechService.getText(file,pythonScriptPath);
    }
}