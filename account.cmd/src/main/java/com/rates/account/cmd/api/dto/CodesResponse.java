package com.rates.account.cmd.api.dto;

import com.rates.account.common.dto.BaseResponse;
import com.rates.account.common.dto.MessageResponse;
import com.rates.currency.api.model.websideline.WebSideLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodesResponse extends BaseResponse {
    private String id;
    private List<WebSideLine> codes;

    public CodesResponse(MessageResponse message, String id, List<WebSideLine> codes) {
        super(message);
        this.id = id;
        this.codes = codes;
    }
}
