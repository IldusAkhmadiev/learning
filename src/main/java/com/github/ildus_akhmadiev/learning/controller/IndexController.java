package com.github.ildus_akhmadiev.learning.controller;

import com.github.ildus_akhmadiev.learning.dto.LessonResultSummaryDTO;
import com.github.ildus_akhmadiev.learning.model.UserLessonResult;
import com.github.ildus_akhmadiev.learning.service.UserLessonResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserLessonResultService userLessonResultService;

    @GetMapping("/")
    public String index (Model model) {
        List<LessonResultSummaryDTO> bestResultAndAttemptCountByLessonId =
                userLessonResultService.getBestResultAndAttemptCountByLessonId();
        model.addAttribute("totalAttempts", bestResultAndAttemptCountByLessonId);
        return "index";
    }
}
