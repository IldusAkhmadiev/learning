package com.github.ildus_akhmadiev.learning.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.web.reactive.function.client.WebClient;


/***
 * Не поднимать вес контекст для теста
 * контекст не грязный.
 */
@SpringBootTest(classes = {WebClientConfig.class})
public class WebClientConfigTest {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private ApplicationContext context;
    @Test
    public void testWebClientBuilder() {
        assertNotNull(webClientBuilder);
        WebClient webClient = webClientBuilder.build();
        assertNotNull(webClient);
    }
}
