package com.rates.account.query.infrastructure.handlers;

import com.rates.account.common.event.CloseAccountEvent;
import com.rates.account.common.event.CurrencyExportOpenedEvent;
import com.rates.account.common.event.CurrencyRequestOpenedEvent;
import com.rates.account.query.domain.entity.Currency;
import com.rates.account.query.domain.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyEventHandler implements EventHandler {

    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * @param event
     */
    @Override
    public void on (CurrencyRequestOpenedEvent event) {
        Currency toSave = Currency.builder()
                .id(event.getId())
                .currencyCode(event.getCurrencyCode())
                .currencyDate(event.getCurrencyDate())
                .creationDate(event.getCreationDate())
                .userName(event.getUserName())
                .tableType(event.getTableType())
                .build();
        currencyRepository.save(toSave);
    }

    /**
     * @param event
     */
    @Override
    public void on(CurrencyExportOpenedEvent event) {
        // TODO: 18.04.2023 Find other solution
        currencyRepository.findByCurrencyCode(event.getCurrencyCode());
    }

    /**
     * @param event
     */
    @Override
    public void on(CloseAccountEvent event) {
        // TODO: 18.04.2023 Find other solution
    }
}
