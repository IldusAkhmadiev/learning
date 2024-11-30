package com.github.ildus_akhmadiev.learning.service;

import com.github.ildus_akhmadiev.learning.dto.LessonResultSummaryDTO;
import com.github.ildus_akhmadiev.learning.dto.UserLessonResultDTO;
import com.github.ildus_akhmadiev.learning.model.UserLessonResult;
import com.github.ildus_akhmadiev.learning.repository.UserLessonResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserLessonResultService {

    @Autowired
    private UserLessonResultRepository userLessonResultRepository;

    public UserLessonResult saveUserLessonResult(UserLessonResultDTO userLessonResultDTO) {
        UserLessonResult userLessonResult = new UserLessonResult();
        userLessonResult.setUserId(userLessonResultDTO.getUserId());
        userLessonResult.setLessonId(userLessonResultDTO.getLessonId());
        userLessonResult.setResultPercent(userLessonResultDTO.getResultPercent());
        userLessonResult.setCompletedAt(userLessonResultDTO.getCompletedAt());

        return userLessonResultRepository.save(userLessonResult);
    }

    public List<UserLessonResult> getBestResultByLessonId(Long lessonId) {
        return userLessonResultRepository.findBestResultByLessonId(lessonId);
    }

    public Long getTotalAttemptsByLessonId(Long lessonId) {
        return userLessonResultRepository.countAttemptsByLessonId(lessonId);
    }
    public List<UserLessonResult> getLessonsWithMaxResult() {
        return userLessonResultRepository.findLessonsWithMaxResult();
    }

    public String getUserLessonResultsSummary(Long lessonId) {
        List<UserLessonResult> bestResults = getBestResultByLessonId(lessonId);
        Long totalAttempts = getTotalAttemptsByLessonId(lessonId);
        StringBuilder summary = new StringBuilder();
        summary.append(String.format("%-20s%-20s%-20s%n", "Урок и его номер", "Лучший результат", "Количество попыток"));
        for (UserLessonResult result : bestResults) {
            summary.append(String.format("%-20s%-20s%-20s%n", "Урок " + result.getLessonId(), result.getResultPercent() + "%", totalAttempts));
        }
        return summary.toString();
    }
    public List<LessonResultSummaryDTO> getBestResultAndAttemptCountByLessonId() {
        return userLessonResultRepository.findBestResultAndAttemptCountByLessonId();
    }
}
