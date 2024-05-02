package com.farm.farm_management.config;

import com.farm.farm_management.entity.CropTypeEntity;
import com.farm.farm_management.entity.SeasonEntity;
import com.farm.farm_management.repo.CropTypeRepo;
import com.farm.farm_management.repo.SeasonRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseRunner implements CommandLineRunner {
    private final SeasonRepo seasonRepo;
    private final CropTypeRepo cropTypeRepo;

    public DatabaseRunner(SeasonRepo seasonRepo, CropTypeRepo cropTypeRepo){
        this.seasonRepo = seasonRepo;
        this.cropTypeRepo = cropTypeRepo;
    }

    @Override
    public void run(String... args){
        if(seasonRepo.count() == 0){
            List<SeasonEntity> seasons = Arrays.asList(
                    new SeasonEntity("Winter", "2023-12-21", "2024-03-19"),
                    new SeasonEntity("Spring", "2024-03-20", "2024-06-20"),
                    new SeasonEntity("Summer", "2024-06-21", "2024-09-22"),
                    new SeasonEntity("Autumn", "2024-09-23", "2024-12-20")
            );
            seasonRepo.saveAll(seasons);
        }
        if(cropTypeRepo.count()==0){
            List<CropTypeEntity> cropTypes = Arrays.asList(
                    new CropTypeEntity("Potato"),
                    new CropTypeEntity("Corn")
            );
            cropTypeRepo.saveAll(cropTypes);
        }
    }
}
