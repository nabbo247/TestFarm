package com.farm.farm_management.dto;

import lombok.Data;

@Data
public class ReportDTO {
    private String season;
    private String crop;
    private double actualHarvestAmount;
    private double expectedProductAmount;
}
