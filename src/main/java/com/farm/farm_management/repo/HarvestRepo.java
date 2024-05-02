package com.farm.farm_management.repo;

import com.farm.farm_management.entity.HarvestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HarvestRepo extends JpaRepository<HarvestEntity, Long> {
    @Query(value = "SELECT h FROM HarvestEntity h WHERE h.farmId =:farmId AND h.plantingId =:plantingId")
    Optional<HarvestEntity> isHarvestExist (@Param("farmId") Integer farmId, @Param("plantingId") Integer plantingId);

    Optional<HarvestEntity> findByPlantingId(Integer plantingId);
}
