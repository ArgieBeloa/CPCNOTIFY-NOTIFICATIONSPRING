package com.example.Notify.ExpoNotify;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expo")
public class ExpoPushNotificationController {

    private final ExpoPushNotificationService expoService;

    @Autowired
    public ExpoPushNotificationController(ExpoPushNotificationService expoService) {
        this.expoService = expoService;
    }

    /**
     * Endpoint to send push notifications to multiple Expo tokens.
     *
     * Example POST /expo/send
     * Body:
     * {
     *   "tokens": ["ExponentPushToken[xxxx]", "ExponentPushToken[yyyy]"],
     *   "title": "Hello",
     *   "message": "This is a test notification"
     * }
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendPushNotification(@RequestBody PushNotificationRequest request) {
        expoService.sendPushNotifications(request.getTokens(), request.getTitle(), request.getMessage());
        return ResponseEntity.ok("Notifications sent");
    }

    // DTO for request body
    public static class PushNotificationRequest {
        private List<String> tokens;
        private String title;
        private String message;

        public List<String> getTokens() {
            return tokens;
        }

        public void setTokens(List<String> tokens) {
            this.tokens = tokens;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
