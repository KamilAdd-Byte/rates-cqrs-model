package com.rates.account.cmd.infrastructure.store;

import com.rates.account.cmd.domain.EventStoreRepository;
import com.rates.account.cmd.domain.aggregate.CurrencyRequestAggregate;
import com.rates.core.events.BaseEvent;
import com.rates.core.events.EventModel;
import com.rates.core.exceptions.AggregateNotFoundException;
import com.rates.core.exceptions.ConcurrencyException;
import com.rates.core.infrastructures.EventStore;
import com.rates.core.kafka.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "currencyStore")
public class CurrencyRequestEventStore implements EventStore {

    @Autowired
    private EventProducer producer;

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
                    .eventData(event)
                    .build();

            EventModel eventToSave = eventStoreRepository.save(eventModel);

            if (!eventToSave.getId().isEmpty()) {
                producer.produce(eventToSave.getClass().getSimpleName(), eventToSave.getEventData());
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

    /**
     * @return list all Event Model when are all currencies
     */
    @Override
    public List<String> getAggregateIds() {
        List<EventModel> eventModels = eventStoreRepository.findAll();
        if (eventModels == null || eventModels.isEmpty()) {
            throw AggregateNotFoundException.becauseAggregateIDIsIncorrect();
        }
        return eventModels.stream()
                .map(EventModel::getAggregateIdentifier)
                .distinct()
                .collect(Collectors.toList());
    }
}
