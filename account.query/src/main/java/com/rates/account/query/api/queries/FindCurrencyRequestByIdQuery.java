package com.rates.account.query.api.queries;

import com.rates.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCurrencyRequestByIdQuery extends BaseQuery {
    private String id;
}
