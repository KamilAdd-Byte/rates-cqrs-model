package com.rates.account.query.api.queries;

import com.rates.account.query.domain.entity.Currency;
import com.rates.account.query.domain.repository.CurrencyRepository;
import com.rates.core.domain.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CurrencyQueryHandler implements QueryHandler {


    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * @param query
     * @return
     */
    @Override
    public List<BaseEntity> handle(FindAllCurrencyRequests query) {
        Iterable<Currency> currencies = currencyRepository.findAll();
        List<BaseEntity> currenciesList = new ArrayList<>();
        currencies.forEach(currenciesList::add);
        return currenciesList;
    }

    /**
     * @param query
     * @return
     */
    @Override
    public List<BaseEntity> handle(FindCurrencyRequestByIdQuery query) {
        Optional<Currency> currencyOptional = currencyRepository.findById(query.getId());
        if (currencyOptional.isEmpty()) {
            log.warn("Currency by id doesn't exists");
            return null;
        }
        List<BaseEntity> currenciesList = new ArrayList<>();
        currenciesList.add(currencyOptional.get());
        return currenciesList;
    }
}
