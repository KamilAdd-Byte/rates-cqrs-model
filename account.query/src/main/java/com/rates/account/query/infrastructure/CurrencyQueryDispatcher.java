package com.rates.account.query.infrastructure;

import com.rates.core.domain.entity.BaseEntity;
import com.rates.core.infrastructures.QueryDispatcher;
import com.rates.core.queries.BaseQuery;
import com.rates.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();
    /**
     * @param type
     * @param handler
     * @param <T>
     */
    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        List<QueryHandlerMethod> queryHandlerMethods = routes.computeIfAbsent(type, c -> new LinkedList<>());
        queryHandlerMethods.add(handler);
    }

    /**
     * @param query
     * @param <U>
     * @return
     */
    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        List<QueryHandlerMethod> queryHandlerMethods = routes.get(query.getClass());
        if (queryHandlerMethods == null || queryHandlerMethods.size() <= 0) {
            throw new RuntimeException("No query handler was registered!");
        }
        if (queryHandlerMethods.size() > 1) {
            throw new RuntimeException("Cannot send query to more than one handler!");
        }

        return queryHandlerMethods.get(0).handle(query);
    }
}
