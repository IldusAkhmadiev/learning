package com.github.ildus_akhmadiev.learning.integration.dictionaryapidev;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UniversalResponse {
    ErrorResponse errorResponse;
    List<WordResponse> wordResponse;
}
