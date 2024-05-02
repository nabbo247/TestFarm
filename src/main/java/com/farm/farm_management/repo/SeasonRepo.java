package com.farm.farm_management.repo;

import com.farm.farm_management.entity.SeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepo extends JpaRepository<SeasonEntity,Long> {
}
