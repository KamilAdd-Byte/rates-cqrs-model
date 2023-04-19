package com.rates.account.cmd.api.dto;

import com.rates.account.common.dto.BaseResponse;
import com.rates.account.common.dto.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodesResponse extends BaseResponse {
    private String id;
    private List<String> codes;

    public CodesResponse(MessageResponse message, String id, List<String> codes) {
        super(message);
        this.id = id;
        this.codes = codes;
    }
}
