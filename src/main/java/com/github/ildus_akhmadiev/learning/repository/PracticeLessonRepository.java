package com.github.ildus_akhmadiev.learning.repository;


import com.github.ildus_akhmadiev.learning.model.PracticeLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticeLessonRepository extends JpaRepository<PracticeLesson, Long> {
    // Методы для поиска уроков по типу
    List<PracticeLesson> findByLessonType(String lessonType);
}