package com.example.Notify.ExpoNotify;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ExpoPushNotificationService {

    private static final String EXPO_PUSH_URL = "https://exp.host/--/api/v2/push/send";

    private final RestTemplate restTemplate;

    public ExpoPushNotificationService() {
        this.restTemplate = new RestTemplate();
    }

    public void sendPushNotifications(List<String> expoTokens, String title, String body) {
        List<Map<String, Object>> messages = new ArrayList<>();

        for (String token : expoTokens) {
            Map<String, Object> message = new HashMap<>();
            message.put("to", token);
            message.put("sound", "default");
            message.put("title", title);
            message.put("body", body);
            messages.add(message);
        }

        // Expo API allows batch sending up to 100 messages at a time
        int batchSize = 100;
        for (int i = 0; i < messages.size(); i += batchSize) {
            List<Map<String, Object>> batch = messages.subList(i, Math.min(i + batchSize, messages.size()));
            HttpHeaders headers = new HttpHeaders();  // use default constructor
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<List<Map<String, Object>>> request = new HttpEntity<>(batch, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(EXPO_PUSH_URL, request, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                // handle error (log, retry, etc.)
                System.err.println("Failed to send notification batch: " + response.getBody());
            }
        }
    }
}
