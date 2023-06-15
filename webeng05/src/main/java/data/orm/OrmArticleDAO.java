package data.orm;

import java.util.List;


import data.ArticleDAO;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import transfer.Article;

public class OrmArticleDAO extends OrmDaoBase implements ArticleDAO {
	
	
	
	@Override
    public Article get(long id) {
    	return getDatabase().find(Article.class, id);  
	
    }
     
    @Override
    public List<Article> getAll() {
    	return getDatabase().find(Article.class).findList();  
    }
     
    @Override
    public void add(Article article) {
    	
    	getDatabase().insert(article); 
    }
     
    @Override
    public void update(Article article) {
    	getDatabase().save(article);     
    	
    }
     
    @Override
    public void delete(Article article) {
    	
    	getDatabase().delete(article);  
    }
}
