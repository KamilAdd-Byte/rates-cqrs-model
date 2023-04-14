package com.rates.account.cmd.domain;

import com.rates.account.cmd.api.command.OpenCurrencyRequestCommand;
import com.rates.account.common.event.CloseAccountEvent;
import com.rates.account.common.event.CurrencyExportOpenedEvent;
import com.rates.account.common.event.CurrencyRequestOpenedEvent;
import com.rates.cqrs.core.domain.AggregateID;
import com.rates.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class CurrencyRequestAggregate extends AggregateRoot {
    private Boolean active;

    public CurrencyRequestAggregate(OpenCurrencyRequestCommand command) {
        raiseEvent(CurrencyRequestOpenedEvent.builder()
                .id(command.getId())
                .tableType(command.getTableType())
                .createdDate(new Date())
                .date(command.getDate())
                .build());
    }

    public void apply (CurrencyRequestOpenedEvent event) {
        this.aggregateID = new AggregateID(event.getId());
        this.active = true;
    }

    public void exportCurrencyToCSV() {
        if (Boolean.FALSE.equals(this.active)) {
            throw new IllegalStateException("Currency cannot be export into a deactivated Aggregate");
        }
        raiseEvent(CurrencyExportOpenedEvent.builder()
                .id(this.aggregateID.getId())
                .build());
    }

    public void close() {
        if (Boolean.FALSE.equals(this.active)) {
            throw new IllegalStateException("Currency cannot be export into a deactivated Aggregate");
        }
        raiseEvent(CloseAccountEvent.builder()
                .id(this.aggregateID.getId())
                .build());
    }
    public void apply (CloseAccountEvent event) {
        this.aggregateID = new AggregateID(event.getId());
        this.active = true;
    }

}