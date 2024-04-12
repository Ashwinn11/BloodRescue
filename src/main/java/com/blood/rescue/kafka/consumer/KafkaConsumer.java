package com.blood.rescue.kafka.consumer;

import com.blood.rescue.dto.Event;

import com.blood.rescue.service.NotificationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);


    private final NotificationService notificationService;

    public KafkaConsumer( NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "blood-available",groupId = "blood-rescue",containerFactory = "containerFactory")
    public void listen(Event event){
        notificationService.notifyAllUsers(event);
        logger.info("Notified all the users");
        notificationService.notifyRecipient(event);
        logger.info("Notified the recipients with user data");

    }
}
