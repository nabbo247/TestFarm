package com.farm.farm_management.repo;

import com.farm.farm_management.entity.PlantingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlantingRepo extends JpaRepository<PlantingEntity, Long> {
    @Query(value = "SELECT p FROM PlantingEntity p WHERE p.cropTypeId =:cropTypeId AND p.seasonId =:seasonId")
    Optional<PlantingEntity> isPlantingExist (@Param("cropTypeId") Integer cropTypeId, @Param("seasonId") Integer seasonId);

    List<PlantingEntity> findByFarmId(Integer farmId);
}
