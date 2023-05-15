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

    @Override
    public String toString() {
        String[] name = fullName.split(" ");
        return name[0] + " " + name[1].toUpperCase().charAt(0) + ". " + name[2].toUpperCase().charAt(0) + '.';
    }
}
