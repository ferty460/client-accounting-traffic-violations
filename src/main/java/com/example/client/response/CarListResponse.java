package com.example.client.response;

import com.example.client.entity.CarEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CarListResponse extends BaseResponse {

    private List<CarEntity> data;
    public CarListResponse(List<CarEntity> data) {
        super(true, "Автомобили");
        this.data = data;
    }
}
