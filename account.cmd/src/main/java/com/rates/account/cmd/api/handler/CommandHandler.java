package com.rates.account.cmd.api.handler;

import com.rates.account.cmd.api.command.CloseAccountCommand;
import com.rates.account.cmd.api.command.ExportCurrencyCommand;
import com.rates.account.cmd.api.command.CurrencyRequestCommand;
import com.rates.account.cmd.api.command.CodesCurrenciesCommand;

public interface CommandHandler {
    void handle (CodesCurrenciesCommand command);
    void handle (CurrencyRequestCommand command);
    void handle (ExportCurrencyCommand command);
    void handle (CloseAccountCommand command);
}
