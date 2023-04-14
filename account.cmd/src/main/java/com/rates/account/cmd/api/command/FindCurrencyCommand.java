package com.rates.account.cmd.api.command;

import com.rates.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class FindCurrencyCommand extends BaseCommand {
    private String currencyToFind;
}
