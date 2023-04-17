package com.rates.account.query.domain.repository;

import com.rates.account.query.domain.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<Currency, String> {
        Optional<Currency> findByCurrencyCode (String code);
}
