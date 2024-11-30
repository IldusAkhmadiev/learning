package com.github.ildus_akhmadiev.learning.model;

import com.github.ildus_akhmadiev.learning.enums.PartOfSpeech;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String original;

    @Column(nullable = false)
    private String transcription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PartOfSpeech partOfSpeech;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Translation> translations = new ArrayList<>();

    // Getters and setters
}
