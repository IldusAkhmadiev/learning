package com.github.ildus_akhmadiev.learning.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenAIService {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${openai.api-key}")
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getChatCompletion(String prompt) throws IOException {
        RequestBody requestBody = RequestBody.create(
                objectMapper.writeValueAsString(new ChatRequest(prompt)),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .header("Authorization", "Bearer " + apiKey)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String responseBody = response.body().string();
            ChatResponse chatResponse = objectMapper.readValue(responseBody, ChatResponse.class);
            return chatResponse.getChoices()[0].getMessage().getContent();
        }
    }

    private static class ChatRequest {
        private final String model = "gpt-3.5-turbo"; // Укажите модель
        private final Message[] messages;

        public ChatRequest(String prompt) {
            this.messages = new Message[]{new Message("user", prompt)};
        }

        public String getModel() {
            return model;
        }

        public Message[] getMessages() {
            return messages;
        }
    }

    private static class Message {
        private final String role;
        private final String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }
    }

    private static class ChatResponse {
        private Choice[] choices;

        public Choice[] getChoices() {
            return choices;
        }

        public void setChoices(Choice[] choices) {
            this.choices = choices;
        }

        private static class Choice {
            private Message message;

            public Message getMessage() {
                return message;
            }

            public void setMessage(Message message) {
                this.message = message;
            }
        }
    }
}
