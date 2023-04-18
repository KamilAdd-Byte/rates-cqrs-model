package com.rates.account.query.infrastructure.consumer;

import com.rates.account.common.event.CloseAccountEvent;
import com.rates.account.common.event.CurrencyExportOpenedEvent;
import com.rates.account.common.event.CurrencyRequestOpenedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume (@Payload CurrencyRequestOpenedEvent event, Acknowledgment acknowledgment);
    void consume (@Payload CurrencyExportOpenedEvent event, Acknowledgment acknowledgment);
    void consume (@Payload CloseAccountEvent event, Acknowledgment acknowledgment);

}
