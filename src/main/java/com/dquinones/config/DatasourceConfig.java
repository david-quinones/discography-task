package com.dquinones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Class to configure the database connection.
 *
 */

@Configuration
public class DatasourceConfig {

    /**
     * Configure and returns the data source for app
     * <p>
     * Url and driverClassNAme has to be the same as application.properties
     *
     * @return configured dataSource
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:discographytask.db");
        return dataSource;
    }

}