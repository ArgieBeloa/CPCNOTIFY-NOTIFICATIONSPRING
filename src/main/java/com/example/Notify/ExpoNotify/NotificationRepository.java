package com.example.Notify.ExpoNotify;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NotificationRepository extends MongoRepository<NotificationModel, String> {
    Optional<NotificationModel> findByDepartment(String department);
}
