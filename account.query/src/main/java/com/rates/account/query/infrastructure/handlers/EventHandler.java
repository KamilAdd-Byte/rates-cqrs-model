package com.rates.account.query.infrastructure.handlers;

import com.rates.account.common.event.CloseAccountEvent;
import com.rates.account.common.event.CurrencyExportOpenedEvent;
import com.rates.account.common.event.CurrencyRequestOpenedEvent;

/**
 * The EventHandler is responsible to update the read database via the CurrencyRepository
 * after a new event was consumed from Kafka
 */
public interface EventHandler {
    void on (CurrencyRequestOpenedEvent event);
    void on (CurrencyExportOpenedEvent event);
    void on (CloseAccountEvent event);

}
