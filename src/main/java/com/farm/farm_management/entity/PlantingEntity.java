package com.farm.farm_management.entity;

import com.farm.farm_management.pojo.PlantingData;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "plantings")
public class PlantingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer farmId;
    private Integer seasonId;
    private Integer cropTypeId;
    private Double expectedProductAmount;

    public PlantingEntity() {
    }
    public PlantingEntity(PlantingData plantingData){
        this.farmId = plantingData.getFarmId();
        this.seasonId =plantingData.getSeasonId();
        this.cropTypeId = plantingData.getCropTypeId();
        this.expectedProductAmount = plantingData.getExpectedProductAmount();
    }
}
