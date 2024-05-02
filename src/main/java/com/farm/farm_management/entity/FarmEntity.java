package com.farm.farm_management.entity;

import com.farm.farm_management.pojo.FarmData;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "farms")
public class FarmEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private double plantingArea;

    public FarmEntity() {
    }
    public FarmEntity(FarmData farmData){
        this.name = farmData.getFarmName();
        this.location = farmData.getFarmLocation();
        this.plantingArea = farmData.getPlantingArea();
    }
}
