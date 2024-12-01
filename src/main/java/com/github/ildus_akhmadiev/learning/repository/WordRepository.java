package com.github.ildus_akhmadiev.learning.repository;

import com.github.ildus_akhmadiev.learning.enums.PartOfSpeech;
import com.github.ildus_akhmadiev.learning.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByPartOfSpeech(PartOfSpeech partOfSpeech);

    @Query("SELECT DISTINCT t.translation FROM Word w JOIN w.translations t WHERE w.partOfSpeech = :partOfSpeech")
    List<String> findDistinctTranslationsByPartOfSpeech(@Param("partOfSpeech") PartOfSpeech partOfSpeech);
}

