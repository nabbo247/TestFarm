package com.farm.farm_management.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PlantingData {
    @NotNull(message = "Farm Id cannot be empty")
    @Positive(message = "Farm Id must be a positive number")
    private Integer farmId;
    @NotNull(message = "Season Id cannot be empty")
    @Positive(message = "Season Id must be a positive number")
    private Integer seasonId;
    @NotNull(message = "Crop Type Id cannot be empty")
    @Positive(message = "Crop Type Id must be a positive number")
    private Integer cropTypeId;
    @NotNull(message = "Expected Product Amount cannot be empty")
    @Positive(message = "Expected Product Amount must be a positive number")
    private Double expectedProductAmount;
}
