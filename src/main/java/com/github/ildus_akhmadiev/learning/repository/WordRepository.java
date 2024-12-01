package com.github.ildus_akhmadiev.learning.repository;

import com.github.ildus_akhmadiev.learning.enums.PartOfSpeech;
import com.github.ildus_akhmadiev.learning.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByPartOfSpeech(PartOfSpeech partOfSpeech);
}

