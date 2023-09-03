package com.anomalys.rest.api.license.Repository;

import com.anomalys.rest.api.license.Entity.License;
import com.anomalys.rest.api.license.Entity.TypeStatus;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.Types;
import java.util.Optional;


/**
 * reference for JdbcTemplate operation
 *
 * -Spring framework documentation
 * {@<a href="link">https://docs.spring.io/spring-framework/docs/2.5.5/reference/jdbc</a>.html}
 * - website reference
 * {@<a href="link">https://www.concretepage.com/spring/spring-jdbctemplate-queryforobject</a>.html}
 * */
@Slf4j
public class LicenseRepositoryImpl implements LicenseRepository {

    private final JdbcTemplate jdbcTemplate;

    public LicenseRepositoryImpl(HikariDataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * find license data with licenseKey
     *
     * @param    licenseKey string data type
     * @return   optional<license> Optional.ofNullable or optional.Empty
     * @throws   IncorrectResultSizeDataAccessException if data not found
     * @throws   DataAccessException error data access, something wrong on query
     * */
    @Override
    public Optional<License> findLicenseByLicenseKey(String licenseKey)
    {
        String sql      = """
                SELECT *
                FROM license
                WHERE licensekey = ?
                """;

        // args is data for binding query is used for fill the values (?,?,?)
        // type is data type for binding query, its depend on data type mysql.
        // there no string, please check link above
        Object[] args   = {licenseKey};
        int[] type      = {Types.VARCHAR};

        try {
            // license mapper used for data when they found it, passing to the object
            License license = jdbcTemplate.queryForObject(sql, args, type, new LicenseMapper());

            log.info("data was found id "+licenseKey);
            return Optional.ofNullable(license);

        }catch (IncorrectResultSizeDataAccessException exception) {
            log.info("data not found id "+licenseKey);
            log.info(exception.getMessage());
            return Optional.empty();

        }catch (DataAccessException exception) {
            log.error("error data access, please check the query or see error message");
            log.error(exception.getMessage());
            return Optional.empty();
        }
    }

    /**
     * inserting license data to database, the object stored contains id, status and licenseKey
     *
     * @param   license object, use license.generatedLicenseKey() for creating data
     *                  don't manual create id, licenseKey for object
     * @return  row affected ( return 1 for success and 0 for failed )
     * @throws  DataAccessException insert data failed when the data exist on database
     * */
    @Override
    public int insertLicenseKey(License license)
    {
        String sql    = """
                INSERT INTO license (id,status,licensekey)
                VALUES (?,?,?)
                """;

        // args is data for binding query is used for fill the values (?,?,?)
        Object[] args = {license.getId(),license.getStatus().getValues(),license.getLicenseKey()};

        // it will result 1 if success or 0 when fail get any reason
        try{
            jdbcTemplate.update(sql,args);
            log.info("success insert data "+license);
            return 1;
        }catch (DataAccessException exception){
            log.error("insert data failed --> "+exception.getCause());
            return 0;
        }
    }

    /**
     * this method used for updating status type license data, using this after you got
     * license object from database. Best practice to use it is after find data and update the object status
     *
     * Note : this method need for upgrade, caused it will be done when the object
     *
     * @param license the license object will be updated status
     * @param newStatusType the new status will be replaced old status on license object
     * @throws DataAccessException failed update, may cause wrong query
     * */
    @Override
    public void updateStatusLicenseKey(License license, TypeStatus newStatusType) {
        String sql = """
                UPDATE license 
                SET status = ?
                WHERE licensekey = ?
                """;

        // args is data for binding query is used for fill the values (?,?,?)
        Object[] args = {newStatusType.getValues(), license.getLicenseKey()};

        try{
            jdbcTemplate.update(sql, args);
            log.info("success  update data "+license);

        }catch (DataAccessException exception){
            log.info("Update status gagal "+exception.getCause());
        }

    }

}
