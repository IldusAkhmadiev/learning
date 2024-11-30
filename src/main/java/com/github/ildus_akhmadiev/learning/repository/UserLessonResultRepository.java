package com.github.ildus_akhmadiev.learning.repository;

import com.github.ildus_akhmadiev.learning.dto.LessonResultSummaryDTO;
import com.github.ildus_akhmadiev.learning.model.UserLessonResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserLessonResultRepository extends JpaRepository<UserLessonResult, Long> {
    // Метод для получения наилучшего результата для каждого урока
    @Query("SELECT u FROM UserLessonResult u " +
            "WHERE u.resultPercent = (SELECT MAX(resultPercent) " +
            "                         FROM UserLessonResult " +
            "                         WHERE lessonId = u.lessonId) AND u.lessonId = :lessonId")
    List<UserLessonResult> findBestResultByLessonId(@Param("lessonId") Long lessonId);
    // Метод для получения общего количества попыток для каждого урока
    @Query("SELECT COUNT(u) FROM UserLessonResult u WHERE u.lessonId = :lessonId")
    Long countAttemptsByLessonId(@Param("lessonId") Long lessonId);

    @Query("SELECT u FROM UserLessonResult u WHERE u.resultPercent = (SELECT MAX(u.resultPercent) FROM UserLessonResult)")
    List<UserLessonResult> findLessonsWithMaxResult();

    // Используем JPQL для создания экземпляров DTO напрямую
    @Query("SELECT new com.github.ildus_akhmadiev.learning.dto.LessonResultSummaryDTO(" +
            "u.lessonId, MAX(u.resultPercent), COUNT(u)) " +
            "FROM UserLessonResult u " +
            "GROUP BY u.lessonId")
    List<LessonResultSummaryDTO> findBestResultAndAttemptCountByLessonId();

    @Query("SELECT new com.github.ildus_akhmadiev.learning.dto.LessonResultSummaryDTO(" +
            "u.lessonId, MAX(u.resultPercent), COUNT(u)) " +
            "FROM UserLessonResult u " +
            "WHERE u.userId = :userId " +
            "GROUP BY u.lessonId")
    List<LessonResultSummaryDTO> findBestResultAndAttemptCountByLessonId(@Param("userId") String userId);

}
