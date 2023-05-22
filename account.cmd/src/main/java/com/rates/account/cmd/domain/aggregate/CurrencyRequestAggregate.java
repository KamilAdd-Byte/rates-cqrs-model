package com.rates.account.cmd.domain.aggregate;

import com.rates.account.cmd.api.command.CurrencyRequestCommand;
import com.rates.account.common.event.CloseAccountEvent;
import com.rates.account.common.event.CurrencyExportOpenedEvent;
import com.rates.account.common.event.CurrencyRequestOpenedEvent;
import com.rates.core.domain.AggregateID;
import com.rates.core.domain.AggregateRoot;
import com.rates.core.domain.AggregateVersion;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class CurrencyRequestAggregate extends AggregateRoot {

    public CurrencyRequestAggregate(CurrencyRequestCommand command) {
        raiseEvent(CurrencyRequestOpenedEvent.builder()
                .id(command.getId())
                .currencyCode(command.getCurrencyCode())
                .tableType(command.getTableType())
                .creationDate(new Date())
                .currencyDate(command.getDate())
                .userName(command.getUsername())
                .build());
    }

    public void apply (CurrencyRequestOpenedEvent event) {
        this.aggregateID = new AggregateID(event.getId());
        this.aggregateVersion = new AggregateVersion(-1);
    }

    public void exportCurrencyToCSV() {
        raiseEvent(CurrencyExportOpenedEvent.builder()
                .id(this.aggregateID.getId())
                .build());
    }

    public void close() {
        raiseEvent(CloseAccountEvent.builder()
                .id(this.aggregateID.getId())
                .build());
    }
    public void apply (CloseAccountEvent event) {
        this.aggregateID = new AggregateID(event.getId());
    }
}