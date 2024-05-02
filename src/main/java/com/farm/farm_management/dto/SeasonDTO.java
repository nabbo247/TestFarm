package com.farm.farm_management.dto;

import lombok.Data;

@Data
public class SeasonDTO {
    private Long id;
    private String name;
    private String startDate;
    private String endDate;
}
