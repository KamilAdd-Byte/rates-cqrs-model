package com.rates.account.cmd.infrastructure;

import com.rates.account.cmd.domain.CurrencyRequestAggregate;
import com.rates.cqrs.core.domain.AggregateRoot;
import com.rates.cqrs.core.domain.AggregateVersion;
import com.rates.cqrs.core.events.BaseEvent;
import com.rates.cqrs.core.handlers.EventSourcingHandler;
import com.rates.cqrs.core.infrastructures.EventStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CurrencyEventSourcingHandler implements EventSourcingHandler<CurrencyRequestAggregate> {

    @Autowired
    private EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregateRoot) {
        eventStore.saveEvents(aggregateRoot.getAggregateID().getId(), aggregateRoot.getUncommittedChanges(), aggregateRoot.getAggregateVersion().getVersion());
        aggregateRoot.markChangesAsCommitted();
    }

    @Override
    public CurrencyRequestAggregate getById(String aggregateId) {
        CurrencyRequestAggregate currencyRequestAggregate = new CurrencyRequestAggregate();
        List<BaseEvent> events = eventStore.getEvents(aggregateId);
        if (events != null && !events.isEmpty()) {
            currencyRequestAggregate.replayEvents(events);
            Optional<Integer> latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            currencyRequestAggregate.setVersion(new AggregateVersion(latestVersion.get()));
        }

        return currencyRequestAggregate;
    }
}
