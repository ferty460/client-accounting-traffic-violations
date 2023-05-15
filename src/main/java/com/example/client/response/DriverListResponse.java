package com.example.client.response;

import com.example.client.entity.DriverEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DriverListResponse extends BaseResponse {

    private List<DriverEntity> data;
    public DriverListResponse(List<DriverEntity> data) {
        super(true, "Водители");
        this.data = data;
    }
}
