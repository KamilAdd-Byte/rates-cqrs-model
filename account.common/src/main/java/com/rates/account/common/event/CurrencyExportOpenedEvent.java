package com.rates.account.common.event;

import com.rates.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CurrencyExportOpenedEvent extends BaseEvent {
    private String currencyCode;
}
