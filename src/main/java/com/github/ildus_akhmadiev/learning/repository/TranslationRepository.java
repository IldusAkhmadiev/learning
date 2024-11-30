package com.github.ildus_akhmadiev.learning.repository;

import com.github.ildus_akhmadiev.learning.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<Translation, Long> {
}
