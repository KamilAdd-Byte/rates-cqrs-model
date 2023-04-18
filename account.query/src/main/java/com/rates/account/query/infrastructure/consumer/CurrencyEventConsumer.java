package com.rates.account.query.infrastructure.consumer;

import com.rates.account.common.event.CloseAccountEvent;
import com.rates.account.common.event.CurrencyExportOpenedEvent;
import com.rates.account.common.event.CurrencyRequestOpenedEvent;
import com.rates.account.query.infrastructure.handlers.EventHandler;
import com.rates.core.events.BaseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class CurrencyEventConsumer implements EventConsumer {

    @Autowired
    private EventHandler eventHandler;

    /**
     * @param event to consume
     * @param acknowledgment Recipients can store the reference in asynchronous scenarios,
                             but the internal state should be assumed transient
                             (i.e. it cannot be serialized and deserialized later)
     */
    @KafkaListener(topics = "CurrencyRequestOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(CurrencyRequestOpenedEvent event, Acknowledgment acknowledgment) {
        eventHandler.on(event);
        acknowledgment.acknowledge();
    }

    /**
     * @param event to consume
     * @param acknowledgment Recipients can store the reference in asynchronous scenarios,
    but the internal state should be assumed transient
    (i.e. it cannot be serialized and deserialized later)
     */
    @KafkaListener(topics = "CurrencyRequestOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(CurrencyExportOpenedEvent event, Acknowledgment acknowledgment) {
        eventHandler.on(event);
        acknowledgment.acknowledge();
    }

    /**
     * @param event to consume
     * @param acknowledgment Recipients can store the reference in asynchronous scenarios,
    but the internal state should be assumed transient
    (i.e. it cannot be serialized and deserialized later)
     */
    @KafkaListener(topics = "CurrencyRequestOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(CloseAccountEvent event, Acknowledgment acknowledgment) {
        eventHandler.on(event);
        acknowledgment.acknowledge();
    }
}
