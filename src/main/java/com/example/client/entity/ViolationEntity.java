package com.example.client.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ViolationEntity {

    private Long violation_Id;

    private CarEntity car;

    private DriverEntity driver;

    private PenaltyEntity penalty;

    private Integer paid;

    private Date time;
}
