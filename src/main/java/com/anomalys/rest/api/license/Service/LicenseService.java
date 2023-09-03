package com.anomalys.rest.api.license.Service;

import com.anomalys.rest.api.license.DTO.WebRequest;
import com.anomalys.rest.api.license.Entity.License;
import com.anomalys.rest.api.license.Exception.CheckedExceptionService;

public interface LicenseService {
    void generateLicenseKey() ;
    License findLicenseKey(WebRequest webRequest) throws CheckedExceptionService;
    License updateStatusLicenseKeyToMati(License license);
}
