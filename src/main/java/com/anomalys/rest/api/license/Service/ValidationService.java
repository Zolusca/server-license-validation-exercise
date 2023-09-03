package com.anomalys.rest.api.license.Service;

public class ValidationService {

    public static <T> Boolean isInstanceClass(Class<T> classType,Object value){
        if(classType.isInstance(value)){
            return true;
        }
        return false;
    }
}
