package com.rates.account.common.event;

import com.rates.core.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class CloseAccountEvent extends BaseEvent {
    public CloseAccountEvent(int version) {
        super(version);
    }
}
