package com.rates.account.common.event;

import com.rates.account.common.dto.TableType;
import com.rates.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CurrencyRequestOpenedEvent extends BaseEvent {
    private String userName;
    private String currencyCode;
    private TableType tableType;
    private String currencyDate;
    private Date creationDate;
}
