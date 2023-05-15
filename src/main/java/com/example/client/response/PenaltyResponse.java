package com.example.client.response;

import com.example.client.entity.PenaltyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PenaltyResponse extends BaseResponse {

    private PenaltyEntity data;
    public PenaltyResponse(boolean success, String message, PenaltyEntity data){
        super(success,message);
        this.data = data;
    }
    public PenaltyResponse(PenaltyEntity data) {
        super(true, "Penalty data");
    }
}
