package com.anomalys.rest.api.license;

import com.anomalys.rest.api.license.Entity.License;
import com.anomalys.rest.api.license.Repository.LicenseRepositoryImpl;
import com.anomalys.rest.api.license.Service.LicenseServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.naming.CommunicationException;
import java.sql.SQLException;

@Configuration
@Slf4j
public class ConfigurationPack {
    private final String url        ="jdbc:mysql://localhost/licensedata";
    private final String driver     ="com.mysql.cj.jdbc.Driver";
    private final String username   ="root";
    private final String password   ="root";

    @Bean(name = "datasource")
    public HikariDataSource getDataSource()
    {
        HikariConfig hikariConfig = new HikariConfig();

        log.info("Start Set hikari config (url,driver,etc)");

        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setMaxLifetime(10 * 60_000);

        log.info("Success set hikari config (url,driver,poolSize,MaxLifeTime)");
        log.info("Start initialize HikariDatasource");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        log.info("Success create connection to database, HikariDataSource is ready");

        return dataSource;
    }

    @Bean
    @DependsOn(value = "datasource")
    public LicenseRepositoryImpl getLicenseRepository(HikariDataSource dataSource){
        LicenseRepositoryImpl licenseRepository = new LicenseRepositoryImpl(dataSource);
        return licenseRepository;
    }

    @Bean
    public License getLicense(){
        License license = new License();
        return license;
    }

    @Bean
    public LicenseServiceImpl getLicenseService(LicenseRepositoryImpl licenseRepository,
                                                License license)
    {
        LicenseServiceImpl licenseService = new LicenseServiceImpl(licenseRepository,license);
        return licenseService;
    }



}
