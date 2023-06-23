package com.shopmeowmeow.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * provides static configuration properties for database-Connections and DAO-TYpe
 */
public class Configuration {
    private static String username;
    private static String password;
    private static String driver;
    private static String url;
    private static String dataSourceType;

    static {
        try {
            final Context env = (Context)new InitialContext().lookup("java:comp/env");
            dataSourceType = (String)env.lookup("dataSourceType");
            username = (String)env.lookup("db.user");
            password = (String)env.lookup("db.password");
            driver = (String)env.lookup("db.driver");
            url = (String)env.lookup("db.url");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @return the username
     */
    public static String getUsername() {
        return username;
    }
    /**
     * @param username the username to set
     */
    public static void setUsername(String username) {
        Configuration.username = username;
    }
    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public static void setPassword(String password) {
        Configuration.password = password;
    }
    /**
     * @return the driver
     */
    public static String getDriver() {
        return driver;
    }
    /**
     * @param driver the driver to set
     */
    public static void setDriver(String driver) {
        Configuration.driver = driver;
    }
    /**
     * @return the url
     */
    public static String getUrl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public static void setUrl(String url) {
        Configuration.url = url;
    }
    /**
     * @return the dataSourceType
     */
    public static String getDataSourceType() {
        return dataSourceType;
    }
    /**
     * @param dataSourceType the dataSourceType to set
     */
    public static void setDataSourceType(String dataSourceType) {
        Configuration.dataSourceType = dataSourceType;
    }


}
