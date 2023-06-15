package data.list;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.ArticleDAO;
import transfer.Article;

public class ListArticleDAO implements ArticleDAO {
    
    private static HashMap<Long,Article> articles = new HashMap<>();
    
    private static Long nextId = 0L;
    
	@Override
    public Article get(long id) {
    	Article a = null;
	
		  a = articles.get(id);
		  if(a==null)
			  return null;
		  return new Article(a.getId(), a.getName(), a.getPrice(), a.getQuantity());
    	
    }
     
    @Override
    public List<Article> getAll() {
    	List<Article> copy = new ArrayList<>();
    	for (Article a : articles.values())
    	{
    		copy.add(new Article(a.getId(), a.getName(), a.getPrice(), a.getQuantity()));
    	}
        return copy;
    }
     
    @Override
    public void add(Article a) {
    	if(a == null)
    		return;
    	Article data = new Article(0, a.getName(), a.getPrice(), a.getQuantity());
    	articles.put(nextId,data);    	
    	a.setId(nextId);
    	data.setId(nextId);
    	nextId++;
    	
    }
     
    @Override
    public void update(Article a) {
    	if(a == null)
    		return;
    	Article article = articles.get(a.getId());
    	article.setName(a.getName());
    	article.setPrice(a.getPrice());
    	article.setQuantity(a.getQuantity());

    }
     
    @Override
    public void delete(Article a) {
    	if(a == null)
    		return;
    	
    	articles.remove(a.getId());
    }
}
