package com.github.ildus_akhmadiev.learning.repository;

import com.github.ildus_akhmadiev.learning.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByLessonId(Integer lessonId);
    Question findByText(String questionText);
    Integer getQuestionIdByText(String questionText);
    @Query("SELECT COUNT(q) FROM Question q WHERE q.lesson.id = :lessonId")
    Integer getCountByLessonId(Integer lessonId);
}
