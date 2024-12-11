package com.github.ildus_akhmadiev.learning.config;

import com.github.ildus_akhmadiev.learning.handler.OAuth2LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private final OAuth2LoginSuccessHandler oauth2LoginSuccessHandler;

    public SecurityConfig(OAuth2LoginSuccessHandler oauth2LoginSuccessHandler) {
        this.oauth2LoginSuccessHandler = oauth2LoginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/index", "/login", "/api/docs/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Разрешаем доступ к Swagger UI и API docs
                        .anyRequest().authenticated() // Остальные запросы требуют аутентификации
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Кастомная страница логина
                        .successHandler(oauth2LoginSuccessHandler) // Подключение кастомного SuccessHandler
                ).logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID","XSRF-TOKEN", "OAuth2-Token") // Удаляем дополнительные куки (если они используются для OAuth2)
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                // Отключение CSRF для конкретных URL
                                new AntPathRequestMatcher("/api/v1/public/**")
                        ));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:8080")); // Ваш фронтенд
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Разрешённые методы
        corsConfig.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization")); // Разрешённые заголовки
        corsConfig.setAllowCredentials(true); // Разрешить использование cookie

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Применяем CORS ко всем запросам

        return source;
    }
    // можно кастомизировать .изучить.
    private OidcUserService oidcUserService() {
        return new OidcUserService(); }
}