package com.farm.farm_management.pojo;

import lombok.Data;

@Data
public class BaseResponse {
    private String statusCode;
    private String message;
    private Object data;

    public BaseResponse() {
    }
    public BaseResponse(boolean error){
        this.statusCode = "1";
        this.message = "Unsuccessful";
        this.data = new Object[0] ;
    }
}
