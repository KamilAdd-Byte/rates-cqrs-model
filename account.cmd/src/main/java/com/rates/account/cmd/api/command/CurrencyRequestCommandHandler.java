package com.rates.account.cmd.api.command;

import com.rates.account.cmd.domain.CurrencyRequestAggregate;
import com.rates.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRequestCommandHandler implements CommandHandler {
    @Autowired
    @Qualifier("currencySourcingHandler")
    private EventSourcingHandler<CurrencyRequestAggregate> eventSourcingHandler;

    @Override
    public void handle(OpenCurrencyRequestCommand command) {
        CurrencyRequestAggregate currencyRequestAggregate = new CurrencyRequestAggregate(command);
        eventSourcingHandler.save(currencyRequestAggregate);
    }

    @Override
    public void handle(ExportCurrencyCommand command) {
        CurrencyRequestAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.exportCurrencyToCSV();
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(CloseAccountCommand command) {
        CurrencyRequestAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.close();
        eventSourcingHandler.save(aggregate);
    }
}
