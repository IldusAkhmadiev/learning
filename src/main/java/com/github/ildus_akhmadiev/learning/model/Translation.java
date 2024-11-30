package com.github.ildus_akhmadiev.learning.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String translation;

    @Column(nullable = false)
    private String contextOriginal;

    @Column(nullable = false)
    private String contextTranslated;

    // Getters and setters
}
