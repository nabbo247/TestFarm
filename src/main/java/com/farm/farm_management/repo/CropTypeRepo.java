package com.farm.farm_management.repo;

import com.farm.farm_management.entity.CropTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropTypeRepo extends JpaRepository<CropTypeEntity, Long> {
}
