package com.github.ildus_akhmadiev.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/learn/eng/practice/results") // Ограничиваем доступ к этому endpoint
                .allowedOrigins("http://localhost:8080") // Указываем ваш домен
                .allowedMethods("POST", "GET") // Разрешённые методы
                .allowedHeaders("*") // Какие заголовки разрешены
                .allowCredentials(true); // Разрешение передачи cookie

        registry.addMapping("/api/v1/public/**") // Ограничиваем доступ к этому endpoint
                .allowedOrigins("*") // Указываем ваш домен
                .allowedMethods("POST", "GET") // Разрешённые методы
                .allowedHeaders("*") // Какие заголовки разрешены
                .allowCredentials(false); // Разрешение передачи cookie
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));  // Разрешаем запросы с любых источников
        configuration.addAllowedMethod("*"); // Разрешаем любые HTTP-методы
        configuration.addAllowedHeader("*"); // Разрешаем любые заголовки
        configuration.setAllowCredentials(true); // Разрешаем куки/авторизационные данные

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Применяем конфигурацию ко всем путям
        return source;
    }
}