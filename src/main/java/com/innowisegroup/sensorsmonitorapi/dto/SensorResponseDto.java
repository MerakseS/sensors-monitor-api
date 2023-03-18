package com.innowisegroup.sensorsmonitorapi.dto;

import lombok.Data;

@Data
public class SensorResponseDto {

    private Long id;
    private String name;
    private String model;
    private int rangeFrom;
    private int rangeTo;
    private String type;
    private String unit;
    private String location;
    private String description;
}
