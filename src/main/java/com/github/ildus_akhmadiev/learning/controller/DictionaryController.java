package com.github.ildus_akhmadiev.learning.controller;

import com.github.ildus_akhmadiev.learning.integration.dictionaryapidev.DictionaryApiDevService;
import com.github.ildus_akhmadiev.learning.integration.dictionaryapidev.DictionaryRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DictionaryController {

    @GetMapping("/dictionary/eng")
    public String eng() {
        return "dictionary";
    }
}
