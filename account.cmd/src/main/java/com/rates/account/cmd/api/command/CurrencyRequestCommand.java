package com.rates.account.cmd.api.command;

import com.rates.account.common.dto.TableType;
import com.rates.core.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRequestCommand extends BaseCommand {
    private String username;
    private String currencyCode;
    private TableType tableType;
    private String date;
}
