package com.anomalys.rest.api.license.Service;

import com.anomalys.rest.api.license.ConfigurationPack;
import com.anomalys.rest.api.license.DTO.WebRequest;
import com.anomalys.rest.api.license.Entity.License;
import com.anomalys.rest.api.license.Exception.CheckedExceptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

//TODO buat testing untuk find license,dan buat controller
//TODO ini sampai testing service insert license

@ContextConfiguration(classes = ConfigurationPack.class)
public class LicenseServiceTestSuccess {
    ApplicationContext context;
    LicenseServiceImpl licenseService;

    @BeforeEach
    public void setUp(){
        this.context = new AnnotationConfigApplicationContext(ConfigurationPack.class);
        this.licenseService = context.getBean(LicenseServiceImpl.class);
    }

    @Test
    @DisplayName(value = "Generate License")
    public void generateLicenseKey(){
        licenseService.generateLicenseKey();
    }

    @Test
    @DisplayName(value = "Find License")
    public void findLicense(){
        WebRequest webRequest = new WebRequest();
        webRequest.setLicense("adminlicense");

        try {
            License license = licenseService.findLicenseKey(webRequest);
            Assertions.assertNotNull(license);
        } catch (CheckedExceptionService e) {
            Assertions.assertInstanceOf(CheckedExceptionService.class,e);
        }
    }


}
