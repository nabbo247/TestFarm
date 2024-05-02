package com.farm.farm_management.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class HarvestData {
    @NotNull(message = "Farm Id cannot be empty")
    @Positive(message = "Farm Id must be a positive number")
    private Integer farmId;
    @NotNull(message = "Planting Id cannot be empty")
    @Positive(message = "Planting Id must be a positive number")
    private Integer plantingId;
    @NotNull(message = "Actual Harvested Amount cannot be empty")
    @Positive(message = "Actual Harvested Amount must be a positive number")
    private Double actualHarvestedAmount;
}
