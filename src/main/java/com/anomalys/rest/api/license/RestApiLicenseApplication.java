package com.anomalys.rest.api.license;

import com.anomalys.rest.api.license.Entity.License;
import com.anomalys.rest.api.license.Entity.TypeStatus;
import com.anomalys.rest.api.license.Repository.LicenseRepositoryImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

@SpringBootApplication
public class RestApiLicenseApplication {

	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationPack.class);

		SpringApplication.run(RestApiLicenseApplication.class, args);
	}

}
