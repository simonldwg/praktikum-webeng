package com.shopmeowmeow.persistence.orm;

import com.shopmeowmeow.configuration.Configuration;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;

import java.util.Properties;


public abstract class OrmDaoBase {

    private Database database;
    /**
     * @return the database
     */
    public Database getDatabase() {
        return database;
    }
    @SuppressWarnings("deprecation")
    public OrmDaoBase()
    {

        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        dataSourceConfig.setUsername(Configuration.getUsername());
        dataSourceConfig.setPassword(Configuration.getPassword());
        dataSourceConfig.setUrl(Configuration.getUrl());
        dataSourceConfig.setDriver(Configuration.getDriver());

        // Automatic schema generation: https://ebean.io/docs/ddl-generation/
        Properties properties = new Properties();
        properties.put("ebean.db.ddl.generate", "true");
        properties.put("ebean.db.ddl.run", "false");



        // configuration ...
        DatabaseConfig config = new DatabaseConfig();
        config.loadFromProperties(properties);

        config.setDataSourceConfig(dataSourceConfig);


        // create database instance

        database = DatabaseFactory.create(config);

    }




}
