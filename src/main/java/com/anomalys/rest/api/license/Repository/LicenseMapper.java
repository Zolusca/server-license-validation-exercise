package com.anomalys.rest.api.license.Repository;

import com.anomalys.rest.api.license.Entity.License;
import com.anomalys.rest.api.license.Entity.TypeStatus;
import jakarta.annotation.Nullable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * class pembuatan object license dari fetch jdbctemplate
 * */
public class LicenseMapper implements RowMapper<License> {
    @Nullable
    @Override
    public License mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new License(
                rs.getString("id"),
                rs.getString("licensekey"),
                TypeStatus.valueOf(rs.getString("status")));
    }
}
