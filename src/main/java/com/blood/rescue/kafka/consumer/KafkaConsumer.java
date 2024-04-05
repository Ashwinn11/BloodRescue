package com.blood.rescue.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "donor-available",groupId = "blood-donor")
    public void listen(String message){
        System.out.println("Sent->"+message);
    }
}
