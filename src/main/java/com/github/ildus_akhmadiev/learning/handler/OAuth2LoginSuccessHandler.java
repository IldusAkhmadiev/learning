package com.github.ildus_akhmadiev.learning.handler;


import com.github.ildus_akhmadiev.learning.enums.Role;
import com.github.ildus_akhmadiev.learning.model.User;
import com.github.ildus_akhmadiev.learning.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        // Извлекаем имя пользователя и email из Google OAuth2
        String username = oauth2User.getAttribute("name");
        String email = oauth2User.getAttribute("email");

        // Создаем нового пользователя и сохраняем в базе данных
        User user = new User(email, username);
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser == null) {
            // Если пользователь с таким email не существует, сохраняем нового
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
        } else {
            // Извлекаем роли пользователя
            Set<Role> roles = existingUser.getRoles();

            // Преобразуем роли в формат Spring Security (GrantedAuthority)
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
            }

            // Указываем провайдера OAuth2 (например, "google" для Google)
            String authorizedClientRegistrationId = "google"; // Замените на вашего провайдера, если это не Google

            // Создаем новый объект аутентификации с ролями пользователя
            Authentication newAuth = new OAuth2AuthenticationToken(oauth2User, authorities, authorizedClientRegistrationId);

            // Устанавливаем новую аутентификацию в контекст безопасности
            SecurityContextHolder.getContext().setAuthentication(newAuth);
        }

        // Перенаправляем на домашнюю страницу или страницу после успешной аутентификации
        response.sendRedirect("/");
    }
}