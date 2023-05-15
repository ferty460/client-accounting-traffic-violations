package com.example.client.response;

import com.example.client.entity.CarEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CarResponse extends BaseResponse {

    private CarEntity data;
    public CarResponse(boolean success, String message, CarEntity data){
        super(success,message);
        this.data = data;
    }
    public CarResponse(CarEntity data) {
        super(true, "Car data");
    }
}
