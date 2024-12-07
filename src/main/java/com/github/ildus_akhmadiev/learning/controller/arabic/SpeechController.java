package com.github.ildus_akhmadiev.learning.controller.arabic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("arabicSpeechController")
public class SpeechController {
    @GetMapping("/arabic/speech")
    public String speech() {
        return "/arabic/speech";
    }
}