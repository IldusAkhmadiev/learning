package com.github.ildus_akhmadiev.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterController {

    @GetMapping("/authors")
    public String authors() {
        return "authors";
    }
}
