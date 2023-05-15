package com.example.client.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DriverEntity {

    private Long driver_Id;

    private String fullName;

    private String passportSeries;

    private String passportNumber;

    private Date birthday;

    private List<CarEntity> cars;

    private List<ViolationEntity> violations;
}
