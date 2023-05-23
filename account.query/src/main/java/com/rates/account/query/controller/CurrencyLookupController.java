package com.rates.account.query.controller;

import com.rates.account.common.dto.MessageResponse;
import com.rates.account.query.api.dto.CurrencyLookupResponse;
import com.rates.account.query.api.queries.FindAllCurrencyRequests;
import com.rates.account.query.api.queries.FindCurrencyRequestByIdQuery;
import com.rates.account.query.domain.entity.Currency;
import com.rates.core.infrastructures.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/currencyLookup")
public class CurrencyLookupController {
    private final Logger logger = Logger.getLogger(CurrencyLookupController.class.getName());

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping(path = "/")
    public ResponseEntity<CurrencyLookupResponse> getAllCurrency() {
        try {
            List<Currency> entities = queryDispatcher.send(new FindAllCurrencyRequests());
            if (entities == null || entities.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            CurrencyLookupResponse successfullyReturnedCurrency = CurrencyLookupResponse.builder()
                    .currencies(entities)
                    .message(new MessageResponse("Successfully returned currency")).build();

            return new ResponseEntity<>(successfullyReturnedCurrency, HttpStatus.OK);

        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get all currencies request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new CurrencyLookupResponse(new MessageResponse(safeErrorMessage)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<CurrencyLookupResponse> getCurrencyById(@PathVariable(value = "id") String id) {
        try {
            List<Currency> entities = queryDispatcher.send(new FindCurrencyRequestByIdQuery(id));

            if (entities == null || entities.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            CurrencyLookupResponse successfullyReturnedCurrency = CurrencyLookupResponse.builder()
                    .currencies(entities)
                    .message(new MessageResponse("Successfully returned currency"))
                    .build();

            return new ResponseEntity<>(successfullyReturnedCurrency, HttpStatus.OK);

        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get currency by ID request";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new CurrencyLookupResponse(new MessageResponse(safeErrorMessage)), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}