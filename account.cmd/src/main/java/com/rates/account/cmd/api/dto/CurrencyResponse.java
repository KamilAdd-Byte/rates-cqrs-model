package com.rates.account.cmd.api.dto;

import com.rates.account.common.dto.BaseResponse;
import com.rates.account.common.dto.MessageResponse;
import com.rates.currency.model.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResponse extends BaseResponse {
    private String id;
    private CurrencyDto currencyDto;

    public CurrencyResponse(MessageResponse message, String id, CurrencyDto currencyDto) {
        super(message);
        this.id = id;
        this.currencyDto = currencyDto;
    }
}
