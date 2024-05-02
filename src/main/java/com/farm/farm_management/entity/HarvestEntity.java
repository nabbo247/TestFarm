package com.farm.farm_management.entity;

import com.farm.farm_management.pojo.HarvestData;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "harvests")
public class HarvestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer farmId;
    private Integer plantingId;
    private Double actualHarvestAmount;

    public HarvestEntity() {
    }

    public HarvestEntity(HarvestData harvestData) {
        this.farmId = harvestData.getFarmId();
        this.plantingId = harvestData.getPlantingId();
        this.actualHarvestAmount = harvestData.getActualHarvestedAmount();
    }
}
