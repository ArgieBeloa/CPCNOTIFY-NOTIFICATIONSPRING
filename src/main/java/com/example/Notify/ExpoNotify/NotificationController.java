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

     @GetMapping("/getAll")
     public  ResponseEntity<List<NotificationModel>> getAll(){

         List<NotificationModel> notificationModels = notificationService.getAll();
         return  new ResponseEntity<>(notificationModels, HttpStatus.OK);
     }

     @PostMapping("/notification/add")
     public NotificationModel addNotificationModel(@RequestBody NotificationModel notificationModel){

         return notificationService.addNotificationModel(notificationModel);

     }

//    @PostMapping("/add")
//    public NotificationModel addExpoNotification(@RequestBody NotificationModel newNotificationModel){
//
//
//
//        return  notificationService.addExpoNotification(newNotificationModel);
//    }

    @PostMapping("/add/{department}")
    public String addStudent(@PathVariable String department, @RequestBody StudentExpoData data) {
        notificationService.addStudentDataByDepartment(department, data);
        return "StudentExpoData added for department: " + department;
    }

//    delete notification model by id
    @DeleteMapping("/{id}/delete")
    public  ResponseEntity<?> delete(@PathVariable String id){
       notificationService.deleteNotificationModel(id);
        return ResponseEntity.ok("Notification model Deleted id: "+ id);
    }


}
