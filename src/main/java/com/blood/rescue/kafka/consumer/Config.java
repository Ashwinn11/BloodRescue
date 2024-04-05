package com.blood.rescue.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;


import java.util.HashMap;
import java.util.Map;
@Configuration
public class Config {
    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        Map<String,Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG,"blood-donor");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListener(){
        ConcurrentKafkaListenerContainerFactory<String,String> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory());
        return containerFactory;
    }
}
