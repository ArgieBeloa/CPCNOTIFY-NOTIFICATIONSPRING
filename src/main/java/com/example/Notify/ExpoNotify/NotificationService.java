package com.example.Notify.ExpoNotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationModel addExpoNotification(NotificationModel newNotificationModel){

        return notificationRepository.save(newNotificationModel);
    }

    public NotificationModel addStudentExpoData(String department, StudentExpoData newData) {
        NotificationModel data = notificationRepository.findByDepartment(department)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        data.getStudentExpoData().add(newData);
        return notificationRepository.save(data);
    }




}
