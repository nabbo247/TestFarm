package com.farm.farm_management.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "crop_type")
public class CropTypeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public CropTypeEntity() {
    }
    public CropTypeEntity(String cropName){
        this.name = cropName;
    }
}
