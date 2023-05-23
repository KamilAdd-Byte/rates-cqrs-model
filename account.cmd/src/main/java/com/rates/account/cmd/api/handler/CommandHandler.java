package com.rates.account.cmd.api.handler;

import com.rates.account.cmd.api.command.*;

public interface CommandHandler {
    void handle (CodesCurrenciesCommand command);
    void handle (CurrencyRequestCommand command);
    void handle (ExportCurrencyCommand command);
    void handle (CloseAccountCommand command);
    void handle (RestoreReadDbCommand command);
}
