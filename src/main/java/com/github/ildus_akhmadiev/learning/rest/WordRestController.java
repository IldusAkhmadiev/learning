package com.github.ildus_akhmadiev.learning.rest;

import com.github.ildus_akhmadiev.learning.dto.TranslationDTO;
import com.github.ildus_akhmadiev.learning.dto.WordDTO;
import com.github.ildus_akhmadiev.learning.exception.ResourceNotFoundException;
import com.github.ildus_akhmadiev.learning.model.Translation;
import com.github.ildus_akhmadiev.learning.model.Word;
import com.github.ildus_akhmadiev.learning.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/words")
public class WordRestController {

    private final WordRepository wordRepository;

    @Autowired
    public WordRestController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @PostMapping
    public WordDTO createWord(@RequestBody WordDTO wordDTO) {
        Word word = fromWordDTO(wordDTO);
        // Установите ссылку на слово для каждой трансляции
        for (Translation translation : word.getTranslations()) {
            translation.setWord(word);
        }
        Word savedWord = wordRepository.save(word);
        return toWordDTO(savedWord);
    }

    @GetMapping("/{id}")
    public WordDTO getWord(@PathVariable Long id) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Word not found"));
        return toWordDTO(word);
    }

    @PutMapping("/{id}")
    public WordDTO updateWord(@PathVariable Long id, @RequestBody WordDTO updatedWordDTO) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Word not found"));

        // Обновление данных
        word.setOriginal(updatedWordDTO.getOriginal());
        word.setTranscription(updatedWordDTO.getTranscription());
        word.setPartOfSpeech(updatedWordDTO.getPartOfSpeech());

        List<Translation> updatedTranslations = fromTranslationDTOList(updatedWordDTO.getTranslations());
        for (Translation translation : updatedTranslations) {
            translation.setWord(word);
        }
        word.setTranslations(updatedTranslations);

        Word savedWord = wordRepository.save(word);
        return toWordDTO(savedWord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWord(@PathVariable Long id) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Word not found"));
        wordRepository.delete(word);
        return ResponseEntity.ok().build();
    }

    // Конвертеры для преобразования между сущностями и DTO

    private WordDTO toWordDTO(Word word) {
        WordDTO dto = new WordDTO();
        dto.setOriginal(word.getOriginal());
        dto.setTranscription(word.getTranscription());
        dto.setPartOfSpeech(word.getPartOfSpeech());
        dto.setTranslations(
                word.getTranslations().stream()
                        .map(this::toTranslationDTO)
                        .collect(Collectors.toList())
        );
        return dto;
    }

    private TranslationDTO toTranslationDTO(Translation translation) {
        TranslationDTO dto = new TranslationDTO();
        dto.setLanguage(translation.getLanguage());
        dto.setTranslation(translation.getTranslation());
        dto.setContextOriginal(translation.getContextOriginal());
        dto.setContextTranslated(translation.getContextTranslated());
        return dto;
    }

    private Word fromWordDTO(WordDTO wordDTO) {
        Word word = new Word();
        word.setOriginal(wordDTO.getOriginal());
        word.setTranscription(wordDTO.getTranscription());
        word.setPartOfSpeech(wordDTO.getPartOfSpeech());
        word.setTranslations(fromTranslationDTOList(wordDTO.getTranslations()));
        return word;
    }

    private List<Translation> fromTranslationDTOList(List<TranslationDTO> translationDTOs) {
        return translationDTOs.stream()
                .map(this::fromTranslationDTO)
                .collect(Collectors.toList());
    }

    private Translation fromTranslationDTO(TranslationDTO dto) {
        Translation translation = new Translation();
        translation.setLanguage(dto.getLanguage());
        translation.setTranslation(dto.getTranslation());
        translation.setContextOriginal(dto.getContextOriginal());
        translation.setContextTranslated(dto.getContextTranslated());
        return translation;
    }
}