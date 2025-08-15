package com.example.Notify.ExpoNotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    @Autowired
    private MongoTemplate mongoTemplate;

    public NotificationModel addExpoNotification(NotificationModel newNotificationModel){

        return notificationRepository.save(newNotificationModel);
    }

//    public NotificationModel addStudentExpoData(String department, StudentExpoData newData) {
//        NotificationModel data = notificationRepository.findByDepartment(department)
//                .orElseThrow(() -> new RuntimeException("Data not found"));
//
//        data.getStudentExpoData().add(newData);
//        return notificationRepository.save(data);
//    }

    public List<NotificationModel> getAll(){
       return  notificationRepository.findAll();
    }

    public  NotificationModel addNotificationModel(NotificationModel newNotificationModel){

        return notificationRepository.save(newNotificationModel);
    }

    public void addStudentDataByDepartment(String department, StudentExpoData newData) {
        Query query = new Query(Criteria.where("department").is(department));

        // Add only if not already present
        Update update = new Update().addToSet("studentExpoData", newData);

        mongoTemplate.updateFirst(query, update, NotificationModel.class);
    }

   public  void deleteNotificationModel(String id){
        notificationRepository.deleteById(id);
   }

}
