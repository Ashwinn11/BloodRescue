package com.blood.rescue.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void sendMessage(String topic,String message){
        kafkaTemplate.send(topic,message);
    }
}
