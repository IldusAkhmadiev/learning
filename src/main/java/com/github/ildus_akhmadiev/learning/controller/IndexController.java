package com.github.ildus_akhmadiev.learning.controller;

import com.github.ildus_akhmadiev.learning.dto.LessonResultSummaryDTO;
import com.github.ildus_akhmadiev.learning.model.UserLessonResult;
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

    @GetMapping("/")
    public String index (Model model,
                         @AuthenticationPrincipal OAuth2User oAuth2User) {

        String userId = oAuth2User.getAttribute("sub"); // Уникальный идентификатор пользователя Google
        List<LessonResultSummaryDTO> bestResultAndAttemptCountByLessonId =
                userLessonResultService.getBestResultAndAttemptCountByLessonId(userId);
        model.addAttribute("totalAttempts", bestResultAndAttemptCountByLessonId);
        return "index";
    }
}
