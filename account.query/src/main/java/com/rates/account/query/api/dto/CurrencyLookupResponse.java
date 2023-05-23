package com.rates.account.query.api.dto;

import com.rates.account.common.dto.BaseResponse;
import com.rates.account.common.dto.MessageResponse;
import com.rates.account.query.domain.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyLookupResponse extends BaseResponse {
    private List<Currency> currencies;

    public CurrencyLookupResponse(MessageResponse message) {
        super(message);
    }
}
