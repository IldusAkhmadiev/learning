package com.github.ildus_akhmadiev.learning.integration.dictionaryapidev;

import lombok.Data;

import java.util.List;
@Data
public class WordResponse {

    private String word;
    private List<Phonetic> phonetics;
    private List<Meaning> meanings;
    private License license;
    private List<String> sourceUrls;

    // Getters and setters
    @Data
    public static class Phonetic {
        private String text;
        private String audio;


        // Getters and setters
    }
    @Data
    public static class Meaning {
        private String partOfSpeech;
        private List<Definition> definitions;
        private List<String> synonyms;
        private List<String> antonyms;

        // Getters and setters
    }
    @Data
    public static class Definition {
        private String definition;
        private String example;

        // Getters and setters
    }
    @Data
    public static class License {
        private String name;
        private String url;

        // Getters and setters
    }

    // Getters and setters for the main class fields
}
