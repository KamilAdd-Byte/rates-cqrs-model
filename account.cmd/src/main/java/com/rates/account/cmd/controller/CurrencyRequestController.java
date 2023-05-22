package com.rates.account.cmd.controller;

import com.rates.account.cmd.api.command.CodesCurrenciesCommand;
import com.rates.account.cmd.api.command.CurrencyRequestCommand;
import com.rates.account.cmd.api.dto.CodesResponse;
import com.rates.account.cmd.api.dto.CurrencyResponse;
import com.rates.account.common.dto.BaseResponse;
import com.rates.account.common.dto.MessageResponse;
import com.rates.core.infrastructures.CommandDispatcher;
import com.rates.currency.api.model.websideline.WebSideLine;
import com.rates.currency.nbp.dto.CurrencyDto;
import com.rates.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
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
        List<WebSideLine> allCodes = currencyService.getAllCodes();
        commandDispatcher.send(command);
        return new ResponseEntity<>(new CodesResponse(new MessageResponse("Get currency successfully"),command.getId(), allCodes), HttpStatus.OK);
    }

    @PostMapping(path = "/lastTeen/{code}")
    public ResponseEntity<CurrencyResponse> getLastTenRatesCurrencyBy(@PathVariable("code") String code) {
        logger.log(Level.FINE, "Beginning currency get");
        String id = UUID.randomUUID().toString();
        CurrencyDto toSave = currencyService.getExchangeRatesOfLastTenDays(code);
        return new ResponseEntity<>(new CurrencyResponse(new MessageResponse("Get currency successfully"),id, toSave), HttpStatus.OK);
    }

    @PostMapping(path = "/command")
    public ResponseEntity<BaseResponse> getLastCurrencyBy(@RequestBody CurrencyRequestCommand command) {
        logger.log(Level.FINE, "Beginning currency get");
        String id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            CurrencyDto toSave = currencyService.getCurrency(command.getCurrencyCode(),command.getTableType().name(),command.getDate());
            commandDispatcher.send(command);
            return new ResponseEntity<>(new CurrencyResponse(new MessageResponse("Get currency successfully"), command.getId(), toSave), HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(new MessageResponse(e.toString())), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            String errorMessage = MessageFormat.format("Error while processing request to get currency code {0}.", command.getCurrencyCode());
            logger.log(Level.SEVERE, errorMessage, e);
            return new ResponseEntity<>(new CurrencyResponse(new MessageResponse(errorMessage), id),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
