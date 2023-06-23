package com.shopmeowmeow.persistence;


import com.shopmeowmeow.configuration.Configuration;
import com.shopmeowmeow.persistence.orm.OrmCatDAO;

public class DAOFactory {

    public enum DataSourceType
    {
        ORM
    }


    // getArticleDAO with type stored in Configuration class - preferred way to get DAO
    public static CatDAO getCatDAO() {
        return getCatDAO(Configuration.getDataSourceType());

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

}

