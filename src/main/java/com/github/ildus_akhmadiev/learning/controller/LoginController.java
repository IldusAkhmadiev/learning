package com.github.ildus_akhmadiev.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Principal principal) {
        if (principal != null) {
            return "redirect:/"; // Или другая страница
        }
        return "login";
    }

}