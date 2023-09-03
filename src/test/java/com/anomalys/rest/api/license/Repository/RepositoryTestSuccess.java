package com.anomalys.rest.api.license.Repository;

import com.anomalys.rest.api.license.ConfigurationPack;
import com.anomalys.rest.api.license.Entity.License;
import com.anomalys.rest.api.license.Entity.TypeStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@ContextConfiguration(classes = ConfigurationPack.class)
public class RepositoryTestSuccess {
    public ApplicationContext context ;
    public LicenseRepositoryImpl licenseRepository;

    @BeforeEach
    public void setUp(){
        this.context = new AnnotationConfigApplicationContext(ConfigurationPack.class);
        licenseRepository = context.getBean(LicenseRepositoryImpl.class);
    }

    @Test
    @DisplayName(value = "find License (OK)")
    public void findLicense()
    {
        final String VALUE_FIND_BY_LICENSEKEY = "acD-s1s-lwV80Cg";
        // data dari database
        Optional<License> licensedata      = licenseRepository
                                                .findLicenseByLicenseKey(VALUE_FIND_BY_LICENSEKEY);
        Assertions.assertNotEquals(Optional.empty(),licensedata);
    }

    @Test
    @DisplayName(value = "create license (OK)")
    public void insertLicense()
    {
        License licenseObj = context.getBean(License.class);
        Assertions.assertEquals(1,licenseRepository
                                                .insertLicenseKey(licenseObj.generateLicenseKey()));
    }

    @Test
    @DisplayName(value = "update license (OK)")
    public void updateStatusLicenseKey()
    {
        // update data
        final String VALUE_FIND_BY_LICENSEKEY            = "acD-s1s-lwV80Cg";
        final TypeStatus VALUE_UPDATE_STATUS_LICENSE_KEY = TypeStatus.MATI;

//         object dicari dari database
        Optional<License> license = licenseRepository
                                        .findLicenseByLicenseKey(VALUE_FIND_BY_LICENSEKEY);
        licenseRepository.updateStatusLicenseKey(license.get(),VALUE_UPDATE_STATUS_LICENSE_KEY);
    }
}
