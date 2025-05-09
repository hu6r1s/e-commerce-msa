package com.hu6r1s.notification.notification.repository;

import com.hu6r1s.notification.notification.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
