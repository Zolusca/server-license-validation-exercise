package com.anomalys.rest.api.license;

import com.anomalys.rest.api.license.Repository.LicenseRepositoryImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ConfigurationPack.class)
public class ConnectionTest {
    @Test
    @DisplayName("testing")
    public void contextLoads() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationPack.class);
        LicenseRepositoryImpl licenseRepo = new LicenseRepositoryImpl(context.getBean(HikariDataSource.class));

    }
}
