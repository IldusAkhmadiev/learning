package com.github.ildus_akhmadiev.learning.dto;

import com.github.ildus_akhmadiev.learning.enums.PartOfSpeech;
import lombok.Data;

import java.util.List;

@Data
public class WordDTO {
    private String original;
    private String transcription;
    private PartOfSpeech partOfSpeech;
    private List<TranslationDTO> translations;

    // Getters и Setters
}