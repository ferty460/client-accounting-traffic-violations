package com.example.client.response;

import com.example.client.entity.ViolationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ViolationResponse extends BaseResponse {

    private ViolationEntity data;
    public ViolationResponse(boolean success, String message, ViolationEntity data){
        super(success,message);
        this.data = data;
    }
    public ViolationResponse(ViolationEntity data) {
        super(true, "Driver data");
    }
}
