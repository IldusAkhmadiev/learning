package com.github.ildus_akhmadiev.learning.service;

import com.github.ildus_akhmadiev.learning.dto.UserLessonResultDTO;
import com.github.ildus_akhmadiev.learning.model.UserLessonResult;
import com.github.ildus_akhmadiev.learning.repository.UserLessonResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
