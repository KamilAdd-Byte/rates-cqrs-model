package com.rates.account.cmd.infrastructure.handler;

import com.rates.account.cmd.domain.aggregate.CurrencyRequestAggregate;
import com.rates.core.domain.AggregateRoot;
import com.rates.core.domain.AggregateVersion;
import com.rates.core.events.BaseEvent;
import com.rates.core.handlers.EventSourcingHandler;
import com.rates.core.infrastructures.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
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
            currencyRequestAggregate.setAggregateVersion(new AggregateVersion(latestVersion.get()));
        }

        return currencyRequestAggregate;
    }
}
