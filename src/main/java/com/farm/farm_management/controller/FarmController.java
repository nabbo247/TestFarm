package com.farm.farm_management.controller;

import com.farm.farm_management.pojo.BaseResponse;
import com.farm.farm_management.pojo.HarvestData;
import com.farm.farm_management.service.AppService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

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

public class FarmController {
    @Autowired
    AppService appService;

    @PostMapping("/add_harvest")
    public ResponseEntity<BaseResponse> addHarvest(@Valid @RequestBody HarvestData harvestData){
        BaseResponse baseResponse = appService.addHarvest(harvestData);
        HttpStatus status = (Objects.equals(baseResponse.getStatusCode(), "200") || Objects.equals(baseResponse.getStatusCode(), "400"))
                ? HttpStatus.OK :  HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse,status);
    }
}