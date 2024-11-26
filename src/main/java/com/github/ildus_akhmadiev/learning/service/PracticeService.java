package com.github.ildus_akhmadiev.learning.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PracticeService {
    // Словарь правильных ответов для каждого местоимения
    private static final Map<String, String> CORRECT_TRANSLATIONS = new HashMap<>() {{
        put("I", "я");
        put("You", "ты");
        put("He/She/It", "он");
    }};

    public boolean checkPronounTranslation(String pronoun, String answer) {
        // Проверяем правильность перевода
        return CORRECT_TRANSLATIONS.get(pronoun).equals(answer);
    }

    public String getAnswerFeedback(String pronoun, boolean isCorrect) {
        // Формируем обратную связь
        if (isCorrect) {
            return String.format("Верно! '%s' переводится как '%s'.",
                    pronoun, CORRECT_TRANSLATIONS.get(pronoun));
        } else {
            return String.format("Неверно. '%s' переводится как '%s'.",
                    pronoun, CORRECT_TRANSLATIONS.get(pronoun));
        }
    }
}