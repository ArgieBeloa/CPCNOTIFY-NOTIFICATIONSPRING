package com.example.Notify.ExpoNotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class NotificationController {

    @Autowired
    public NotificationService notificationService;

    @PostMapping("/add")
    public NotificationModel addExpoNotification(@RequestBody NotificationModel newNotificationModel){



        return  notificationService.addExpoNotification(newNotificationModel);
    }

    @PostMapping("/{department}/expoToken")
    public ResponseEntity<NotificationModel> addStudentExpoData(
            @PathVariable String department,
            @RequestBody StudentExpoData newData) {
        NotificationModel updatedNotification = notificationService.addStudentExpoData(department, newData);
        return ResponseEntity.ok(updatedNotification);
    }

}
