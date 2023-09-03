package com.anomalys.rest.api.license.Exception;

public class RuntimeExceptionService extends RuntimeException{

    public RuntimeExceptionService() {
    }

    public RuntimeExceptionService(String message) {
        super(message);
    }
}
