package com.farm.farm_management.service;

import com.farm.farm_management.dto.CropTypeDTO;
import com.farm.farm_management.dto.FarmDTO;
import com.farm.farm_management.dto.ReportDTO;
import com.farm.farm_management.dto.SeasonDTO;
import com.farm.farm_management.entity.*;
import com.farm.farm_management.pojo.BaseResponse;
import com.farm.farm_management.pojo.FarmData;
import com.farm.farm_management.pojo.HarvestData;
import com.farm.farm_management.pojo.PlantingData;
import com.farm.farm_management.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AppService {
    BaseResponse baseResponse = new BaseResponse();
    private static  final Logger LOG = Logger.getLogger(AppService.class.getName());
    @Autowired
    CropTypeRepo cropTypeRepo;
    @Autowired
    SeasonRepo seasonRepo;
    @Autowired
    FarmRepo farmRepo;
    @Autowired
    PlantingRepo plantingRepo;
    @Autowired
    HarvestRepo harvestRepo;


    public BaseResponse listAllSeason(){
        try{
            List<SeasonEntity> getAllSeason = seasonRepo.findAll();
            List<Object> result = new ArrayList<>();
            for(SeasonEntity seasonEntity : getAllSeason){
                SeasonDTO seasonDTO = new SeasonDTO();
                seasonDTO.setId(seasonEntity.getId());
                seasonDTO.setName(seasonEntity.getName());
                seasonDTO.setStartDate(seasonEntity.getStartDate());
                seasonDTO.setEndDate(seasonEntity.getEndDate());
                result.add(seasonDTO);
            }

            baseResponse.setStatusCode("200");
            baseResponse.setMessage("Successful");
            baseResponse.setData(result);
        }
        catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse listAllCrop(){
        try{
            List<CropTypeEntity> getAllCrop = cropTypeRepo.findAll();
            List<Object> result = new ArrayList<>();
            for(CropTypeEntity cropTypeEntity : getAllCrop){
                CropTypeDTO cropTypeDTO = new CropTypeDTO();
                cropTypeDTO.setId(cropTypeEntity.getId());
                cropTypeDTO.setName(cropTypeEntity.getName());
                result.add(cropTypeDTO);
            }

            baseResponse.setStatusCode("200");
            baseResponse.setMessage("Successful");
            baseResponse.setData(result);
        }
        catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse addFarmer(FarmData farmData){
        try{
            //Check if farmName already exist
            Optional<FarmEntity> isFarmNameExist = farmRepo.isFarmNameExist(farmData.getFarmName());
            if(isFarmNameExist.isPresent()){
                baseResponse.setStatusCode("400");
                baseResponse.setMessage("Farm Name already exist, Kindly choose another name");
                baseResponse.setData(new Object[0]);
                return baseResponse;
            }
            FarmEntity farmEntity = new FarmEntity(farmData);
            farmRepo.save(farmEntity);
            baseResponse.setStatusCode("200");
            baseResponse.setMessage("Farmer added successfully");
            baseResponse.setData(new Object[0]);
        }
        catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse listAllFarm(){
        try{
            List<FarmEntity> getAllFarm = farmRepo.findAll();
            List<Object> result = new ArrayList<>();
            for(FarmEntity farmEntity : getAllFarm){
                FarmDTO farmDTO = new FarmDTO();
                farmDTO.setId(farmEntity.getId());
                farmDTO.setFarmName(farmEntity.getName());
                farmDTO.setFarmLocation(farmEntity.getLocation());
                farmDTO.setPlantingArea(String.valueOf(farmEntity.getPlantingArea()) +" acres");
                result.add(farmDTO);
            }

            baseResponse.setStatusCode("200");
            baseResponse.setMessage("Successful");
            baseResponse.setData(result);
        }
        catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse addPlanting(PlantingData plantingData){
        try{
            //Check if farm exist
            Optional<FarmEntity> isFarmIdExist = farmRepo.findById(Long.valueOf(plantingData.getFarmId()));
            if(isFarmIdExist.isEmpty()){
                baseResponse.setStatusCode("400");
                baseResponse.setMessage("Farm Id doesn't exist for a particular farm");
                baseResponse.setData(new Object[0]);
                return baseResponse;
            }
            //Check if crop exist
            Optional<CropTypeEntity> isCropTypeExist = cropTypeRepo.findById(Long.valueOf(plantingData.getCropTypeId()));
            if(isCropTypeExist.isEmpty()){
                baseResponse.setStatusCode("400");
                baseResponse.setMessage("Crop Type Id doesn't exist for a particular crop type");
                baseResponse.setData(new Object[0]);
                return baseResponse;
            }
            //Check if season id exist
            Optional<SeasonEntity> isSeasonExist = seasonRepo.findById(Long.valueOf(plantingData.getSeasonId()));
            if(isSeasonExist.isEmpty()){
                baseResponse.setStatusCode("400");
                baseResponse.setMessage("Season Id doesn't exist for a particular season");
                baseResponse.setData(new Object[0]);
                return baseResponse;
            }
            //Check if crop is planted already within such season
            Optional<PlantingEntity> isPlantingExist = plantingRepo.isPlantingExist(plantingData.getCropTypeId(), plantingData.getSeasonId());
            if(isPlantingExist.isPresent()){
                baseResponse.setStatusCode("400");
                baseResponse.setMessage("Crop is already planted within the season.");
                baseResponse.setData(new Object[0]);
                return baseResponse;
            }
            //Add planting data
            PlantingEntity plantingEntity = new PlantingEntity(plantingData);
            plantingRepo.save(plantingEntity);

            baseResponse.setStatusCode("200");
            baseResponse.setMessage("Planting created successful");
            baseResponse.setData(new Object[0]);
        }
        catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse addHarvest(HarvestData harvestData){
        try{
            //Check if farm exist
            Optional<FarmEntity> isFarmIdExist = farmRepo.findById(Long.valueOf(harvestData.getFarmId()));
            if(isFarmIdExist.isEmpty()){
                baseResponse.setStatusCode("400");
                baseResponse.setMessage("Farm Id doesn't exist for a particular farm");
                baseResponse.setData(new Object[0]);
                return baseResponse;
            }
            //Check if planting id exist
            Optional<PlantingEntity> isPlantingExist = plantingRepo.findById(Long.valueOf(harvestData.getPlantingId()));
            if(isPlantingExist.isEmpty()){
                baseResponse.setStatusCode("400");
                baseResponse.setMessage("Planting Id doesn't exist");
                baseResponse.setData(new Object[0]);
                return baseResponse;
            }
            //Check if planting id exist with farmer
            PlantingEntity plantingEntity = isPlantingExist.get();
            int plantFarmId = plantingEntity.getFarmId();
            if(plantFarmId != harvestData.getFarmId()){
                baseResponse.setStatusCode("400");
                baseResponse.setMessage("Planting Id doesn't associated with Farm Id");
                baseResponse.setData(new Object[0]);
                return baseResponse;
            }

            //Check if harvest with such farmId and plantingId already exist
            Optional<HarvestEntity> isHarvestExist = harvestRepo.isHarvestExist(harvestData.getFarmId(), harvestData.getPlantingId());
            if(isHarvestExist.isPresent()){
                baseResponse.setStatusCode("400");
                baseResponse.setMessage("Harvest record of farm id with such planting id is already recorded");
                baseResponse.setData(new Object[0]);
                return baseResponse;
            }

            HarvestEntity harvestEntity = new HarvestEntity(harvestData);
            harvestRepo.save(harvestEntity);

            baseResponse.setStatusCode("200");
            baseResponse.setMessage("Harvesting recorded successful");
            baseResponse.setData(new Object[0]);
        }
        catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse getReport(Integer farmId){
        try{
            List<PlantingEntity> getAllFarmPlanting = plantingRepo.findByFarmId(farmId);
            if(getAllFarmPlanting.isEmpty()){
                baseResponse.setStatusCode("400");
                baseResponse.setMessage("No record available for such farm");
                baseResponse.setData(new Object[0]);
                return baseResponse;
            }
            List<Object> result = new ArrayList<>();
            for(PlantingEntity plantingEntity : getAllFarmPlanting){
                ReportDTO reportDTO = new ReportDTO();
                //Get Corresponding harvestRecord
                double actualHarvestAmount = 0;
                String cropName ="";
                String seasonName = "";
                Optional<HarvestEntity> getHarvestData = harvestRepo.findByPlantingId(plantingEntity.getId().intValue());
                if(getHarvestData.isPresent()){
                    HarvestEntity harvestEntity = getHarvestData.get();
                    actualHarvestAmount = harvestEntity.getActualHarvestAmount();
                }
                //Get crop name
                Optional<CropTypeEntity> cropData = cropTypeRepo.findById(Long.valueOf(plantingEntity.getCropTypeId()));
                if(cropData.isPresent()){
                    CropTypeEntity cropTypeEntity = cropData.get();
                    cropName = cropTypeEntity.getName();
                }
                //Get season name
                Optional<SeasonEntity> seasonData = seasonRepo.findById(Long.valueOf(plantingEntity.getSeasonId()));
                if(seasonData.isPresent()){
                    SeasonEntity seasonEntity = seasonData.get();
                    seasonName = seasonEntity.getName();
                }

                reportDTO.setCrop(cropName);
                reportDTO.setSeason(seasonName);
                reportDTO.setActualHarvestAmount(actualHarvestAmount);
                reportDTO.setExpectedProductAmount(plantingEntity.getExpectedProductAmount());
                result.add(reportDTO);
            }
            baseResponse.setStatusCode("200");
            baseResponse.setMessage("Successful");
            baseResponse.setData(result);

        }
        catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

}
