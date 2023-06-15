package data;

import configuration.Configuration;
import data.list.ListArticleDAO;
import data.orm.OrmArticleDAO;
import data.sql.SqlArticleDAO;


public class DAOFactory {
	
	public enum DataSourceType
	{
		SQL,
		ORM,
		LIST
	}
	
	
	// getArticleDAO with type stored in Configuration class - preferred way to get DAO
	public static ArticleDAO getArticleDAO() {
		return getArticleDAO(Configuration.getDataSourceType());
		
	}
	
	// getArticleDAO with type given by typeString
	public static ArticleDAO getArticleDAO(String typeString) {
		DataSourceType type = DataSourceType.valueOf(typeString);
		switch(type)
		{
		case LIST:
			return new ListArticleDAO();

		case ORM:
			return new OrmArticleDAO();

		case SQL:
			return new SqlArticleDAO();
		
		default:
			return null;
			
		}
		
	}

}
