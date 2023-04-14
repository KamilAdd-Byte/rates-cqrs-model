package com.rates.account.cmd.infrastructure;

import com.rates.account.cmd.domain.CurrencyRequestAggregate;
import com.rates.account.cmd.domain.EventStoreRepository;
import com.rates.cqrs.core.events.BaseEvent;
import com.rates.cqrs.core.events.EventModel;
import com.rates.cqrs.core.exceptions.AggregateNotFoundException;
import com.rates.cqrs.core.exceptions.ConcurrencyException;
import com.rates.cqrs.core.infrastructures.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyRequestEventStore implements EventStore {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        List<EventModel> eventModels = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventModels.get(eventModels.size() -1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }

        int version = expectedVersion;
        for (BaseEvent event : events) {
            version++;
            event.setVersion(version);

            EventModel eventModel = EventModel.builder()
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(CurrencyRequestAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .build();

            var eventToSave = eventStoreRepository.save(eventModel);
            if (eventToSave != null) {
                // TODO: 14.04.2023 produce Kafka
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        List<EventModel> eventModels = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventModels == null || eventModels.isEmpty()) {
            throw AggregateNotFoundException.becauseProvidedIDIsIncorrect();
        }

        return eventModels.stream().map(EventModel::getEventData).collect(Collectors.toList());
    }
}
