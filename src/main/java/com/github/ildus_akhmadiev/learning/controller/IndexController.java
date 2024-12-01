package com.github.ildus_akhmadiev.learning.controller;

import com.github.ildus_akhmadiev.learning.dto.LessonResultSummaryDTO;
import com.github.ildus_akhmadiev.learning.model.User;
import com.github.ildus_akhmadiev.learning.model.UserLessonResult;
import com.github.ildus_akhmadiev.learning.repository.UserRepository;
import com.github.ildus_akhmadiev.learning.service.UserLessonResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserLessonResultService userLessonResultService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index (Model model) {
        // Получаем всех пользователей, которые прошли аутентификацию через Google
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "index";
    }
}
