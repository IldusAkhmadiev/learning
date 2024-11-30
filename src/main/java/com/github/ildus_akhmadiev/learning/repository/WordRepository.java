package com.github.ildus_akhmadiev.learning.repository;

import com.github.ildus_akhmadiev.learning.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}

