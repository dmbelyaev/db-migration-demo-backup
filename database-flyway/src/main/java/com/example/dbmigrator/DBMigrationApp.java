package com.example.dbmigrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBMigrationApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBMigrationApp.class);

    public static void main(String[] args) {
        LOGGER.info(
                "Executed {} migrations",
                new Migrator(EnvDataSourceProvider.getDataSource()).migrateToLatest().migrationsExecuted);
    }
}
