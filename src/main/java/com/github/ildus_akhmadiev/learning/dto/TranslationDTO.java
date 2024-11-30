package com.github.ildus_akhmadiev.learning.dto;

import lombok.Data;

@Data
public class TranslationDTO {
    private String language;
    private String translation;
    private String contextOriginal;
    private String contextTranslated;

    // Getters и Setters
}