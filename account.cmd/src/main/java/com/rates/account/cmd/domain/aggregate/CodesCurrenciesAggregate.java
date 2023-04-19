package com.rates.account.cmd.domain.aggregate;

import com.rates.account.cmd.api.command.CodesCurrenciesCommand;
import com.rates.account.common.event.CodesCurrenciesEvent;
import com.rates.core.domain.AggregateID;
import com.rates.core.domain.AggregateRoot;
import com.rates.core.domain.AggregateVersion;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
public class CodesCurrenciesAggregate extends AggregateRoot {

    public CodesCurrenciesAggregate(CodesCurrenciesCommand command) {
        raiseEvent(CodesCurrenciesEvent.builder()
                .id(command.getId())
                .creationDate(new Date())
                .build()
        );
    }

    public void apply (CodesCurrenciesEvent event) {
        this.aggregateID = new AggregateID(event.getId());
        this.aggregateVersion = new AggregateVersion(-1);
    }
}
