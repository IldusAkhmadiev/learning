package com.github.ildus_akhmadiev.learning.controller;

import com.github.ildus_akhmadiev.learning.model.User;
import com.github.ildus_akhmadiev.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String getUsers(Model model) {
        // Получаем всех пользователей, которые прошли аутентификацию через Google
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userList"; // Имя шаблона Thymeleaf
    }
}
