package com.github.ildus_akhmadiev.learning.config;

import com.github.ildus_akhmadiev.learning.handler.OAuth2LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
                        .anyRequest().permitAll() // Остальные запросы требуют аутентификации /
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Кастомная страница логина
                        .successHandler(oauth2LoginSuccessHandler) // Подключение вашего кастомного SuccessHandler
                );
        return http.build();
    }
}