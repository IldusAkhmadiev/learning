package com.github.ildus_akhmadiev.learning.controller;

import com.github.ildus_akhmadiev.learning.model.User;
import com.github.ildus_akhmadiev.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "username", required = false) String username,
                        @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        if (username != null && !username.isEmpty()) {
            List<User> users = userService.findUsersByCriteria(username);
            model.addAttribute("users", users);
        } else {
            Page<User> users = userService.findAll(pageable);
            model.addAttribute("users", users.getContent());
            model.addAttribute("totalPages", users.getTotalPages());
            model.addAttribute("currentPage", users.getNumber());
        }
        return "index";
    }
}
