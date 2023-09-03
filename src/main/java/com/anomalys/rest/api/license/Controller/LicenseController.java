package com.anomalys.rest.api.license.Controller;

import com.anomalys.rest.api.license.ConfigurationPack;
import com.anomalys.rest.api.license.DTO.LicenseResponseData;
import com.anomalys.rest.api.license.DTO.WebRequest;
import com.anomalys.rest.api.license.DTO.WebResponse;
import com.anomalys.rest.api.license.Entity.License;
import com.anomalys.rest.api.license.Exception.CheckedExceptionService;
import com.anomalys.rest.api.license.Repository.LicenseRepositoryImpl;
import com.anomalys.rest.api.license.Service.LicenseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class LicenseController{
    private LicenseServiceImpl licenseService;


    public LicenseController() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationPack.class);
        this.licenseService = new LicenseServiceImpl(context.getBean(LicenseRepositoryImpl.class),
                                                     context.getBean(License.class));
    }

    @GetMapping(path = "create",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WebResponse> generateLicense()
    {
        log.info("(Controller)---> Starting generateLicense");

        licenseService.generateLicenseKey();

        WebResponse webResponse = WebResponse
                                            .builder()
                                            .message("sukses")
                                            .statusResponse(HttpStatus.OK)
                                            .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Location","/test");
        httpHeaders.set("Method","Get");
        return ResponseEntity.status(200).headers(httpHeaders).body(webResponse);
     }


    @PostMapping(path = "find"
            ,produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WebResponse> findLicenseKey(@RequestBody WebRequest webRequest){
        log.info("(controller)---> Starting findlicensekey");

        License license ;
        WebResponse webResponse = null;
        LicenseResponseData licenseResponseData;

        try {
            // proses find data dari json request
            license = licenseService.findLicenseKey(webRequest);

            // generate response data
            licenseResponseData = new LicenseResponseData(license.getLicenseKey(),license.getStatus());

            log.info("Data request ditemukan webRequest + "+ webRequest.toString());

            webResponse = WebResponse.builder()
                    .statusResponse(HttpStatus.OK)
                    .message("data ditemukan")
                    .data(licenseResponseData)
                    .build();

        } catch (CheckedExceptionService e)
        {
            log.info("Data request tidak dapat ditemukan webRequest + "+ webRequest.toString());

            webResponse = webResponse.builder()
                    .statusResponse(e.getHttpStatus())
                    .data(e.getDataRequest())
                    .message(e.getMessage())
                    .build();
        }finally {
            return ResponseEntity
                    .status(webResponse.getStatusResponse())
                    .body(webResponse);
        }
    }


    @PostMapping(path = "license/activated"
                ,produces = MediaType.APPLICATION_JSON_VALUE
                ,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WebResponse> updateStatusLicenseKeyToMati(@RequestBody WebRequest webRequest){
        log.info("(controller)---> Starting updateStatusLicenseKeyToMati");

        WebResponse webResponse = null;
        License license                 ;
        LicenseResponseData licenseResponseData;

        // find data from request
        try {
            license = licenseService.findLicenseKey(webRequest);

            // process license service update status
            License newDataFromUpdate = licenseService.updateStatusLicenseKeyToMati(license);

            // create webResponse data from newDataFromUpdate
            licenseResponseData = new LicenseResponseData(newDataFromUpdate.getLicenseKey()
                                                        ,newDataFromUpdate.getStatus());

            webResponse = WebResponse.builder()
                    .statusResponse(HttpStatus.OK)
                    .message("data berhasil diubah")
                    .data(licenseResponseData)
                    .build();

        } catch (CheckedExceptionService e) {
            // give message the licensekey does not exist
            log.info("Data request tidak dapat ditemukan, webRequest  "+ webRequest.toString());

            webResponse = webResponse.builder()
                    .statusResponse(e.getHttpStatus())
                    .data(e.getDataRequest())
                    .message(e.getMessage())
                    .build();

        }finally {
            log.debug("finally block space");

            return ResponseEntity
                    .status(webResponse.getStatusResponse())
                    .body(webResponse);
        }
    }

}

