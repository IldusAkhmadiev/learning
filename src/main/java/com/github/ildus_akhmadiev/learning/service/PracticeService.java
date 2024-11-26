package com.github.ildus_akhmadiev.learning.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PracticeService {
    public boolean checkPronounTranslation(String answer) {
        // Правильный перевод местоимения "I" - "я"
        return "я".equals(answer);
    }

    public String getAnswerFeedback(boolean isCorrect) {
        return isCorrect
                ? "Верно! 'I' переводится как 'я'."
                : "Неверно. 'I' переводится как 'я'.";
    }

}