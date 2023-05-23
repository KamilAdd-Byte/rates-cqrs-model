package com.rates.account.cmd.controller;

import com.rates.account.cmd.api.command.RestoreReadDbCommand;
import com.rates.account.common.dto.BaseResponse;
import com.rates.account.common.dto.MessageResponse;
import com.rates.core.infrastructures.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/restoreReadDb")
public class RestoreReadDbController {
    private static final Logger logger = Logger.getLogger(RestoreReadDbController.class.getName());
    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> restoreReadDb() {
        try {
            //CurrencyDto toSave = currencyService.getCurrency(command.getCurrencyCode(),command.getTableType().name(),command.getDate());
            commandDispatcher.send(new RestoreReadDbCommand());
            return new ResponseEntity<>(new BaseResponse(new MessageResponse("Get currency successfully")), HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(new MessageResponse(e.toString())), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            String errorMessage = "Error while processing request to get currency code.";
            logger.log(Level.SEVERE, errorMessage, e);
            return new ResponseEntity<>(new BaseResponse(new MessageResponse(errorMessage)),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
