package com.example.Notify.ExpoNotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class NotificationSchedulerService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ExpoPushNotificationService expoPushNotificationService;

    // Run every hour (cron expression)
    @Scheduled(cron = "0 */30 * * * *")
    public void sendScheduledNotifications() {
        String department = "IT"; // or get dynamically from somewhere

        List<String> tokens = notificationRepository.findByDepartment(department).stream()
                .flatMap(notification -> notification.getStudentExpoData().stream())
                .map(StudentExpoData::getNotificationId)
                .distinct()
                .collect(Collectors.toList());

        if (!tokens.isEmpty()) {
            expoPushNotificationService.sendPushNotifications(tokens,
                    "Scheduled Notification",
                    "This is your scheduled notification for " + department + " department.");
        }
    }
}
