package com.innowisegroup.sensorsmonitorapi.integrator;


import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class FlywayIntegrator {

    @Resource
    private DataSource dataSource;

    @PostConstruct
    private void onStartUp() {
        Flyway.configure()
            .dataSource(dataSource)
            .load()
            .migrate();
    }
}
