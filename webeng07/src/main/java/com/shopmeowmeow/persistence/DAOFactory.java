package com.shopmeowmeow.persistence;


import com.shopmeowmeow.configuration.Configuration;
import com.shopmeowmeow.persistence.orm.OrmCatDAO;
import com.shopmeowmeow.persistence.orm.OrmOrderDAO;

public class DAOFactory {

    public enum DataSourceType
    {
        ORM
    }


    // getArticleDAO with type stored in Configuration class - preferred way to get DAO
    public static CatDAO getCatDAO() {
        return getCatDAO(Configuration.getDataSourceType());

    }
    public static OrderDAO getOrderDAO() {
        return getOrderDAO(Configuration.getDataSourceType());

    }

    // getArticleDAO with type given by typeString
    public static CatDAO getCatDAO(String typeString) {
        DataSourceType type = DataSourceType.valueOf(typeString);
        switch(type)
        {

            case ORM:
                return new OrmCatDAO();

            default:
                return null;

        }

    }
    public static OrderDAO getOrderDAO(String typeString) {
        DataSourceType type = DataSourceType.valueOf(typeString);
        switch(type)
        {

            case ORM:
                return new OrmOrderDAO();

            default:
                return null;

        }

    }

}

