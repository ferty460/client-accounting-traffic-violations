package com.example.client.response;

import com.example.client.entity.DriverEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DriverResponse extends BaseResponse {

    private DriverEntity data;
    public DriverResponse(boolean success, String message, DriverEntity data){
        super(success,message);
        this.data = data;
    }
    public DriverResponse(DriverEntity data) {
        super(true, "Driver data");
    }
}
