package com.farm.farm_management.controller;

import com.farm.farm_management.pojo.BaseResponse;
import com.farm.farm_management.service.AppService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/farm_management")
@Api(tags = "Farm Management", description = "Farm Management System")
public class ReportController {
    @Autowired
    AppService appService;

    @GetMapping("/report")
    public ResponseEntity<BaseResponse> getFarmReport(@RequestParam("farmId") Integer farmId){
        BaseResponse baseResponse = appService.getReport(farmId);
        HttpStatus status = (Objects.equals(baseResponse.getStatusCode(), "200") || Objects.equals(baseResponse.getStatusCode(), "400"))
                ? HttpStatus.OK :  HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse,status);
    }
}
