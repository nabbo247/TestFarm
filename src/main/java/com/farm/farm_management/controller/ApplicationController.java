package com.farm.farm_management.controller;

import com.farm.farm_management.pojo.BaseResponse;
import com.farm.farm_management.pojo.FarmData;
import com.farm.farm_management.pojo.HarvestData;
import com.farm.farm_management.pojo.PlantingData;
import com.farm.farm_management.service.AppService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/farm_management")
@Api(tags = "Farm Management", description = "Farm Management System")

public class ApplicationController {
    @Autowired
    AppService appService;

    @GetMapping("/list_all_season")
    public ResponseEntity<BaseResponse> listAllSeason(){
        BaseResponse baseResponse = appService.listAllSeason();
        HttpStatus status = (Objects.equals(baseResponse.getStatusCode(), "200") || Objects.equals(baseResponse.getStatusCode(), "400"))
                ? HttpStatus.OK :  HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse,status);
    }

    @GetMapping("/list_all_crop_type")
    public ResponseEntity<BaseResponse> listAllCropType(){
        BaseResponse baseResponse = appService.listAllCrop();
        HttpStatus status = (Objects.equals(baseResponse.getStatusCode(), "200") || Objects.equals(baseResponse.getStatusCode(), "400"))
                ? HttpStatus.OK :  HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse,status);
    }

    @PostMapping("/add_planting")
    public ResponseEntity<BaseResponse> addPlanting(@Valid @RequestBody PlantingData plantingData){
        BaseResponse baseResponse = appService.addPlanting(plantingData);
        HttpStatus status = (Objects.equals(baseResponse.getStatusCode(), "200") || Objects.equals(baseResponse.getStatusCode(), "400"))
                ? HttpStatus.OK :  HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse,status);
    }

}
