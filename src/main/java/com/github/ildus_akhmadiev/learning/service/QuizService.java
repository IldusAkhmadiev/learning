package com.github.ildus_akhmadiev.learning.service;

import com.github.ildus_akhmadiev.learning.enums.PartOfSpeech;
import com.github.ildus_akhmadiev.learning.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Получает правильный перевод @param correctAnswer подбирает к нему неправильные ответы на основе @param totalOptions
 * Смешивает их с неправильными варинтами ответа.
 * Используется validateTotalOptions чтобы избежать ошибок если пользвотель передаст некорректые значения вроде 0,-2
 */
@Service
public class QuizService {

    @Autowired
    private WordRepository wordRepository;

    /**
     * Генерирует список вариантов для вопроса викторины.
     * Обеспечивает наличие достаточного количества вариантов, включая правильный ответ.
     */
    public List<String> generateOptions(String correctAnswer, int totalOptions) {
        totalOptions = validateTotalOptions(totalOptions);
        List<String> allOptions = fetchAllOptions(correctAnswer);
        List<String> wrongOptions = pickWrongOptions(allOptions, totalOptions);
        return assembleFinalOptions(wrongOptions, correctAnswer);
    }

    /**
     * Валидирует количество вариантов, чтобы предотвратить недопустимый ввод.
     * Обеспечивает наличие хотя бы двух вариантов.
     */
    private int validateTotalOptions(int totalOptions) {
        return totalOptions < 2 ? 2 : totalOptions;
    }

    /**
     * Извлекает все возможные варианты, исключая правильный ответ.
     * Фильтрует переводы на основе указанной части речи.
     */
    private List<String> fetchAllOptions(String correctAnswer) {
        List<String> allOptions = wordRepository.findDistinctTranslationsByPartOfSpeech(PartOfSpeech.PRONOUN);
        allOptions.remove(correctAnswer);
        return allOptions;
    }

    /**
     * Выбирает ограниченное количество неверных вариантов из доступных.
     * Перемешивает варианты для случайности.
     */
    private List<String> pickWrongOptions(List<String> allOptions, int totalOptions) {
        Collections.shuffle(allOptions);
        return allOptions.stream()
                .limit(totalOptions - 1)
                .collect(Collectors.toList());
    }

    /**
     * Собирает финальный список вариантов, включая правильный ответ.
     * Перемешивает финальный список для случайности.
     */
    private List<String> assembleFinalOptions(List<String> wrongOptions, String correctAnswer) {
        List<String> options = new ArrayList<>(wrongOptions);
        options.add(correctAnswer);
        Collections.shuffle(options);
        return options;
    }
}