package com.example.client.entity;


import lombok.Data;

import java.util.List;

@Data
public class PenaltyEntity {

    private Long penalty_Id;

    private String kind;

    private String description;

    private int penalty;

    private List<ViolationEntity> violations;
}
