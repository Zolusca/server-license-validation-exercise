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
public class RepositoryTestFailed {
    public ApplicationContext context ;
    public LicenseRepositoryImpl licenseRepository;

    @BeforeEach
    public void setUp(){
        this.context = new AnnotationConfigApplicationContext(ConfigurationPack.class);
        licenseRepository = context.getBean(LicenseRepositoryImpl.class);
    }

    @Test
    @DisplayName("find license (FAILED)")
    // failed when data not on database
    public void findLicense()
    {
        final String VALUE_FIND_BY_LICENSEKEY = "acD-s1s-lwV8mmm";

        Optional<License> licensedata      = licenseRepository
                                                .findLicenseByLicenseKey(VALUE_FIND_BY_LICENSEKEY);
        Assertions.assertEquals(Optional.empty(),licensedata);
    }

    @Test
    @DisplayName(value = "create license (FAILED)")
    // failed when data is exists on database, may generate method generating same value
    public void insertLicense()
    {
        License licenseObj = new License("001aja","adminlicense", TypeStatus.AKTIF);

        Assertions.assertEquals(0,licenseRepository
                                                .insertLicenseKey(licenseObj));
    }

    @Test
    @DisplayName(value = "update license (FAILED)")
    // update license will fail when, query not correct
    public void updateStatusLicenseKey()
    {

    }
}
