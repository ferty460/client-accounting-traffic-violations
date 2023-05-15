package com.example.client.entity;

import lombok.Data;

import java.util.List;

@Data
public class CarEntity {

    private Long car_Id;

    private String brand;

    private String number;

    private DriverEntity driver;

    private List<ViolationEntity> violations;

    @Override
    public String toString() {
        return number;
    }
}
