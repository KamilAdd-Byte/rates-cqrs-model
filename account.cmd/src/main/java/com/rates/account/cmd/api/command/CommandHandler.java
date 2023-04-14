package com.rates.account.cmd.api.command;

public interface CommandHandler {
    void handle (OpenCurrencyRequestCommand command);
    void handle (ExportCurrencyCommand command);
    void handle (CloseAccountCommand command);
}
