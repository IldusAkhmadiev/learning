package com.github.ildus_akhmadiev.learning.repository;

import com.github.ildus_akhmadiev.learning.model.UserLessonResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLessonResultRepository extends JpaRepository<UserLessonResult, Long> {
}
