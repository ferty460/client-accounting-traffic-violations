package com.example.client.response;

import com.example.client.entity.ViolationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ViolationListResponse extends BaseResponse {

    private List<ViolationEntity> data;
    public ViolationListResponse(List<ViolationEntity> data) {
        super(true, "Нарушения");
        this.data = data;
    }
}
