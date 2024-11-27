package com.github.ildus_akhmadiev.learning.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnswerSubmissionDTO {
    private Integer questionId;
    private String answer; // Getters and setters
}