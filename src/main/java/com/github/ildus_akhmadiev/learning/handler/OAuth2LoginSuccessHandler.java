package com.github.ildus_akhmadiev.learning.handler;


import com.github.ildus_akhmadiev.learning.model.User;
import com.github.ildus_akhmadiev.learning.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;


import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        // Извлекаем имя пользователя и email из Google OAuth2
        String username = oauth2User.getAttribute("name");
        String email = oauth2User.getAttribute("email");

        // Создаем нового пользователя и сохраняем в базе данных
        User user = new User(email, username);
        userRepository.save(user);
        // Перенаправляем на домашнюю страницу или страницу после успешной аутентификации
        response.sendRedirect("/list");
    }
}