package com.github.ildus_akhmadiev.learning.config;

import com.github.ildus_akhmadiev.learning.handler.OAuth2LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    private final OAuth2LoginSuccessHandler oauth2LoginSuccessHandler;

    public SecurityConfig(OAuth2LoginSuccessHandler oauth2LoginSuccessHandler) {
        this.oauth2LoginSuccessHandler = oauth2LoginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login").permitAll() // Разрешить доступ к корневой странице и странице логина
                        .anyRequest().permitAll() // Остальные запросы требуют аутентификации
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Кастомная страница логина
                        .successHandler(oauth2LoginSuccessHandler) // Подключение вашего кастомного SuccessHandler
                )
                .cors() // Включаем CORS для всего приложения
                .and()
                .csrf().disable(); // Отключаем CSRF, если необходимо (например, для API)

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Ваш фронтенд
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Разрешённые методы
        corsConfig.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization")); // Разрешённые заголовки
        corsConfig.setAllowCredentials(true); // Разрешить использование cookie

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Применяем CORS ко всем запросам

        return source;
    }
}