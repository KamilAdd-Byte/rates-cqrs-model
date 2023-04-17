package com.rates.account.cmd.infrastructure.producer;

import com.rates.core.events.BaseEvent;
import com.rates.core.kafka.EventProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CurrencyEventProducer implements EventProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * @param topic The basic unit, or rather, the name used to describe a group of logs.
     * @param event basic event which include version.
     */
    @Override
    public void produce(String topic, BaseEvent event) {
        this.kafkaTemplate.send(topic, event);
    }
}
