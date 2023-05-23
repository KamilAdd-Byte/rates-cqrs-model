package com.rates.account.cmd.infrastructure.handler;

import com.rates.account.common.event.CodesCurrenciesEvent;
import com.rates.core.domain.AggregateRoot;
import com.rates.core.handlers.EventSourcingHandler;
import com.rates.core.infrastructures.EventStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CodesEventSourcingHandler implements EventSourcingHandler<CodesCurrenciesEvent> {

    @Autowired
    private EventStore eventStore;


    /**
     * @param aggregateRoot
     */
    @Override
    public void save(AggregateRoot aggregateRoot) {
        eventStore.saveEvents(aggregateRoot.getAggregateID().getId(),aggregateRoot.getUncommittedChanges(),aggregateRoot.getAggregateVersion().getVersion());
        aggregateRoot.markChangesAsCommitted();
    }

    /**
     * @param aggregateId
     * @return
     */
    @Override
    public CodesCurrenciesEvent getById(String aggregateId) {
        return null;
    }

    /**
     *
     */
    @Override
    public void republishEvents() {

    }
}
