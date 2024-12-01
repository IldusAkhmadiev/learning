package com.github.ildus_akhmadiev.learning.service;

import com.github.ildus_akhmadiev.learning.enums.PartOfSpeech;
import com.github.ildus_akhmadiev.learning.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private WordRepository wordRepository;

    public List<String> generateOptions(String correctAnswer, int totalOptions) {
        if (totalOptions < 2) {
            totalOptions = 2;
        }

        // Извлекаем все переводы, кроме правильного ответа
        List<String> allOptions = wordRepository.findDistinctTranslationsByPartOfSpeech(PartOfSpeech.PRONOUN); // Метод, который вернет все уникальные переводы
        allOptions.remove(correctAnswer);

        // Случайным образом выбираем неправильные варианты
        Collections.shuffle(allOptions); // Перемешиваем
        List<String> wrongOptions = allOptions.stream()
                .limit(totalOptions - 1) // Количество неправильных вариантов
                .collect(Collectors.toList());

        // Формируем итоговый список и добавляем правильный ответ
        List<String> options = new ArrayList<>(wrongOptions);
        options.add(correctAnswer);

        // Перемешиваем список
        Collections.shuffle(options);

        return options;
    }
}