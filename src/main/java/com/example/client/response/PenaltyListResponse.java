package com.example.client.response;

import com.example.client.entity.PenaltyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PenaltyListResponse extends BaseResponse {

    private List<PenaltyEntity> data;
    public PenaltyListResponse(List<PenaltyEntity> data) {
        super(true, "Штрафы");
        this.data = data;
    }
}
