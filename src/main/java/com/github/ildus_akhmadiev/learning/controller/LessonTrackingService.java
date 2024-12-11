package com.github.ildus_akhmadiev.learning.controller;

import com.github.ildus_akhmadiev.learning.dto.LessonAnswerDTO;
import com.github.ildus_akhmadiev.learning.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LessonTrackingService {

    @Autowired
    QuestionRepository questionRepository;

    private final Map<Long, Integer> userLessonProgress = new HashMap<>();
    private boolean isDone = false;


    public void submit(Long lessonId, LessonAnswerDTO answerDTO) {
        if(!userLessonProgress.containsKey(lessonId)) {
            Integer countByLessonId = questionRepository.getCountByLessonId(answerDTO.getQuestionId());
            String userId = answerDTO.getUserId();
            userLessonProgress.put(lessonId, countByLessonId);
        }
        userLessonProgress.put(lessonId, userLessonProgress.get(lessonId) - 1);
        if(userLessonProgress.get(lessonId) == 0) {
            isDone = true;
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public void reset() {
        isDone = false;
    }
}

