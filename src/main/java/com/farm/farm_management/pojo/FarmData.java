package com.farm.farm_management.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class FarmData {
    @NotEmpty(message = "Farm Name cannot be empty")
    private String farmName;
    @NotEmpty(message = "Farm Location cannot be empty")
    private String farmLocation;
    @NotNull(message = "Planting area cannot be empty")
    @Positive(message = "Planting area must be a positive number")
    private Double plantingArea;
}
