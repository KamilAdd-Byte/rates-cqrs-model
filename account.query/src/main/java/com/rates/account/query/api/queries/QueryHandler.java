package com.rates.account.query.api.queries;

import com.rates.core.domain.entity.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle (FindAllCurrencyRequests query);
    List<BaseEntity> handle (FindCurrencyRequestByIdQuery query);
}
