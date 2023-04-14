package com.rates.account.cmd.api.command;

import com.rates.account.common.dto.TableType;
import com.rates.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenCurrencyRequestCommand extends BaseCommand {
    private String currencyCode;
    private TableType tableType;
    private String date;
}
