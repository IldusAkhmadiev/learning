package com.github.ildus_akhmadiev.learning.controller.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(Model model, WebRequest webRequest) {
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        int status = (int) errorAttributes.get("status");
        String message = (String) errorAttributes.get("message");
        String error = (String) errorAttributes.get("error");
        String trace = (String) errorAttributes.get("trace");

        model.addAttribute("status", status);
        model.addAttribute("error", error);
        model.addAttribute("message", message);
        model.addAttribute("trace", trace);

        // Определяем, какая страница будет отображаться в зависимости от типа ошибки
        if (status == HttpStatus.NOT_FOUND.value()) {
            return "error/404"; // Страница для 404 ошибки
        } else {
            return "error/anyerror"; // Страница для всех других ошибок
        }
    }
}
