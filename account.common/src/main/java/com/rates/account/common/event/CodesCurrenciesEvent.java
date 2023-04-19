package com.rates.account.common.event;

import com.rates.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CodesCurrenciesEvent extends BaseEvent {
    private Date creationDate;
}
