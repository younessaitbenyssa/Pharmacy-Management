package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

public class Llm {
    private final String apiUrl;
    private final String apiKey;
    private final HttpClient client;
    private final List<Map<String, String>> conversationHistory;

    public Llm() {

        this.apiUrl = "https://api.hyperbolic.xyz/v1/chat/completions";
        this.apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJiaXNvZnRlbmdpbmVlcmluZ2NsdWJAZ21haWwuY29tIiwiaWF0IjoxNzM1NjM3MTAxfQ.e5IK25FLpdbe4VwpPnvv33UxpKCPNLZbUbWVqi65uAk"; // Replace with your actual API key
        this.client = HttpClient.newHttpClient();
        this.conversationHistory = new ArrayList<>();


        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a virtual assistant. Your primary purpose is to provide responses "
                + "that are concise, logically connected, and conversational.");
        this.conversationHistory.add(systemMessage);
    }

    public String sendMessage(String userMessage) throws Exception {
        System.out.println("the user Message: " + userMessage);
        Map<String, String> userMessageMap = new HashMap<>();
        userMessageMap.put("role", "user");
        userMessageMap.put("content", userMessage);
        this.conversationHistory.add(userMessageMap);


        Map<String, Object> data = new HashMap<>();
        data.put("messages", this.conversationHistory);
        data.put("model", "deepseek-ai/DeepSeek-V3");
        data.put("max_tokens", 512);
        data.put("temperature", 0.1);
        data.put("top_p", 0.9);


        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(data);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        if (response.statusCode() == 200) {

            Map<String, Object> responseJson = objectMapper.readValue(response.body(), Map.class);


            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseJson.get("choices");


            if (!choices.isEmpty()) {

                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                String content = (String) message.get("content");
                return content;
            } else {
                throw new RuntimeException("No choices found in the response.");
            }
        } else {
            throw new RuntimeException("Failed to call LLM API: " + response.body());
        }
    }



}
