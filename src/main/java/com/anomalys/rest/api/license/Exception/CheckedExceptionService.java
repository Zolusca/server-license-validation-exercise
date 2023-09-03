package com.anomalys.rest.api.license.Exception;

import org.springframework.http.HttpStatus;

public class CheckedExceptionService extends Exception{
    private HttpStatus httpStatus;
    private Object dataRequest;
    public CheckedExceptionService() {
    }

    public CheckedExceptionService(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public CheckedExceptionService(String message, HttpStatus httpStatus,Object dataRequest) {
        super(message);
        this.httpStatus = httpStatus;
        this.dataRequest = dataRequest;
    }

    public Object getDataRequest() {
        return dataRequest;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

}
