package com.rates.account.cmd.controller;

import com.rates.account.cmd.api.command.CodesCurrenciesCommand;
import com.rates.account.cmd.api.command.CurrencyRequestCommand;
import com.rates.account.cmd.api.dto.CodesResponse;
import com.rates.account.cmd.api.dto.CurrencyResponse;
import com.rates.account.common.dto.MessageResponse;
import com.rates.core.infrastructures.CommandDispatcher;

import com.rates.currency.model.CurrencyDto;
import com.rates.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/currency")
public class CurrencyRequestController {
    private static final Logger logger = Logger.getLogger(CurrencyRequestController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @Autowired
    private CurrencyService currencyService;

    @PostMapping(path = "/codes")
    public ResponseEntity<CodesResponse> getAllCode (@RequestBody CodesCurrenciesCommand command) {
        List<String> allCodes = currencyService.getAllCodes();
        commandDispatcher.send(command);
        return new ResponseEntity<>(new CodesResponse(new MessageResponse("Get currency successfully"),command.getId(), allCodes), HttpStatus.OK);
    }

    @PostMapping(path = "lastTeen/{code}")
    public ResponseEntity<CurrencyResponse> getLastTenRatesCurrencyBy(@PathVariable("code") String code) {
        logger.log(Level.FINE, "Beginning currency get");
        String id = UUID.randomUUID().toString();
        CurrencyDto toSave = currencyService.getExchangeRatesOfLastTenDays(code);
        return new ResponseEntity<>(new CurrencyResponse(new MessageResponse("Get currency successfully"),id, toSave), HttpStatus.OK);
    }

    @PostMapping(path = "/command")
    public ResponseEntity<CurrencyResponse> getLastCurrencyBy(@RequestBody CurrencyRequestCommand command) {
        logger.log(Level.FINE, "Beginning currency get");
        CurrencyDto toSave = currencyService.getCurrency(command.getCurrencyCode(),command.getTableType().name(),command.getDate());
        commandDispatcher.send(command);
        return new ResponseEntity<>(new CurrencyResponse(new MessageResponse("Get currency successfully"), command.getId(), toSave), HttpStatus.OK);
    }
}
