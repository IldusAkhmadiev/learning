package com.github.ildus_akhmadiev.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/learn/eng/practice/results") // Ограничиваем доступ к этому endpoint
                .allowedOrigins("127.0.0.1") // Указываем ваш домен
                .allowedMethods("POST", "GET") // Разрешённые методы
                .allowedHeaders("*") // Какие заголовки разрешены
                .allowCredentials(true); // Разрешение передачи cookie
    }
}