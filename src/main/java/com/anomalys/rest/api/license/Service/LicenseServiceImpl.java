package com.anomalys.rest.api.license.Service;

import com.anomalys.rest.api.license.DTO.WebRequest;
import com.anomalys.rest.api.license.Entity.License;
import com.anomalys.rest.api.license.Entity.TypeStatus;
import com.anomalys.rest.api.license.Exception.CheckedExceptionService;
import com.anomalys.rest.api.license.Repository.LicenseRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Optional;

//TODO perbaiki service implementation agar lebih baik, generate service dsb
@Slf4j
public class LicenseServiceImpl implements LicenseService {
    private LicenseRepositoryImpl licenseRepository;
    private License license;

    public LicenseServiceImpl(LicenseRepositoryImpl licenseRepository, License license) {
        this.licenseRepository = licenseRepository;
        this.license = license;
    }

    /**
     * this method handle generating license data
     * the object was created by the entity license class on method generatedLicenseKey()
     * @see com.anomalys.rest.api.license.Entity.License
     * */
    @Override
    public void generateLicenseKey(){
        log.info("(Service)---> Starting generate licenseKey");
        licenseRepository.insertLicenseKey(license.generateLicenseKey());
    }

    /**
     * this method for finding the data licensekey input from client,
     * where the input on WebRequest class
     * <p>
     *     <pre>
     *    try{
     *         your implementation
     *         -- webResponse build for success
     *       }catch(Checked exception){
     *         -- webResponse build for failed
     *       }finally{
     *           webResponse when success or not
     *       }
     *       </pre>
     *       </p>
     *
     * @param webRequest the value string licensekey from user
     * @return License object when data found
     * @throws CheckedExceptionService is checked exception when data not found
     * */
    @Override
    public License findLicenseKey(WebRequest webRequest) throws CheckedExceptionService {
        log.info("(Service)---> Starting findlicensekey");

        // processing find license data from database
        Optional<License> data = licenseRepository.findLicenseByLicenseKey(
                                                        webRequest.getLicense());

        // check data if empty will throw exception
        if(data.isEmpty()){
            log.debug("data is empty "+ webRequest.toString());

            throw new CheckedExceptionService(
                    "Data tidak ditemukan",
                    HttpStatus.NOT_FOUND,
                    webRequest);
        }
        return data.get();
    }

    /**
     * this method for updating license status to MATI
     *
     * @param license license data object where the status will be replaced to MATI
     * @return license data param
     * */
    @Override
    public License updateStatusLicenseKeyToMati(License license) {
        log.info("(Service)---> Starting update status licenseKey to MATI");

        licenseRepository.updateStatusLicenseKey(license, TypeStatus.MATI);
        license.setStatus(TypeStatus.MATI);
        return license;
    }

}
