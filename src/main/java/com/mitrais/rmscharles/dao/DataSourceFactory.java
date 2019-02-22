package com.mitrais.rmscharles.dao;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.File;

/**
 * This class provides MySQL datasource to be used to connect to database.
 * It implements singleton pattern See <a href="http://www.oodesign.com/singleton-pattern.html">Singleton Pattern</a>
 */
public class DataSourceFactory
{
    private final DataSource dataSource;

    DataSourceFactory()
    {
    	MysqlDataSource dataSource = new MysqlDataSource();
        // TODO: make these database setting configurable by moving to properties file
        dataSource.setDatabaseName("rmsdb");
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUser("rms");
        dataSource.setPassword("rms");
        this.dataSource = dataSource;
    }

    /**
     * Get a data source to database
     *
     * @return DataSource object
     */
    public static Connection getConnection() throws SQLException
    {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper
    {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
}
