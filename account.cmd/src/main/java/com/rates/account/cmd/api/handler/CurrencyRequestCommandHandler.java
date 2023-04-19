package com.rates.account.cmd.api.handler;

import com.rates.account.cmd.api.command.CloseAccountCommand;
import com.rates.account.cmd.api.command.ExportCurrencyCommand;
import com.rates.account.cmd.api.command.CurrencyRequestCommand;
import com.rates.account.cmd.api.command.CodesCurrenciesCommand;
import com.rates.account.cmd.domain.aggregate.CodesCurrenciesAggregate;
import com.rates.account.cmd.domain.aggregate.CurrencyRequestAggregate;
import com.rates.core.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRequestCommandHandler implements CommandHandler {
    @Autowired
    private EventSourcingHandler<CurrencyRequestAggregate> eventSourcingHandler;

    /**
     * @param command
     */
    @Override
    public void handle(CodesCurrenciesCommand command) {
        CodesCurrenciesAggregate codesCurrenciesAggregate = new CodesCurrenciesAggregate(command);
        eventSourcingHandler.save(codesCurrenciesAggregate);
    }

    @Override
    public void handle(CurrencyRequestCommand command) {
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
