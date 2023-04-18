package com.rates.account.cmd.api.command;

import com.rates.account.common.dto.TableType;
import com.rates.core.commands.BaseCommand;
import lombok.Data;
import java.util.Date;

@Data
public class OpenCurrencyRequestCommand extends BaseCommand {
    private String username;
    private String currencyCode;
    private TableType tableType;
    private Date date;
}
