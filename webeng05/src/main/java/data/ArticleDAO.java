package data;

import java.util.List;


import transfer.Article;


public interface ArticleDAO {
    
    Article get(long id);
     
    List<Article> getAll();
     
    void add(Article t);
     
    void update(Article t);
     
    void delete(Article t);
}
