package com.blood.rescue.kafka.producer;

import com.blood.rescue.dto.Event;
import com.blood.rescue.entity.BloodGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaProducer {


    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void sendMessage(String topic, Event event){
        kafkaTemplate.send(topic,event);
    }
}
