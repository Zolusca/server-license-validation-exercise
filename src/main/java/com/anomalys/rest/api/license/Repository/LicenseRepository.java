package com.anomalys.rest.api.license.Repository;

import com.anomalys.rest.api.license.Entity.License;
import com.anomalys.rest.api.license.Entity.TypeStatus;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

public interface LicenseRepository {
    Optional<License> findLicenseByLicenseKey(String license) throws EmptyResultDataAccessException;
    int insertLicenseKey(License license);
    void updateStatusLicenseKey(License license, TypeStatus newStatusType);
}
