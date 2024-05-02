package com.farm.farm_management.repo;

import com.farm.farm_management.entity.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FarmRepo extends JpaRepository<FarmEntity, Long> {
    @Query(value = "SELECT f FROM FarmEntity f WHERE f.name =:farmName")
    Optional<FarmEntity> isFarmNameExist(@Param("farmName") String farmName);
}
