package businessLogic;

import data.ArticleDAO;
import data.DAOFactory;
import transfer.Article;

import java.util.List;

public class ArticleManager {

    private final ArticleDAO articleDAO;

    public ArticleManager() {
        articleDAO = DAOFactory.getArticleDAO();
    }


    public Article changeQuantityBy(Article article, int diff) {
        if(article.getQuantity() + diff < 0) {
            throw new InvalidQuantityException();
        }
        article.setQuantity(article.getQuantity() + diff);
        articleDAO.update(article);
        return article;
    }

    public List<Article> getAllArticles() {
        return articleDAO.getAll();
    }

    public Article getArticle(long id) {
        return articleDAO.get(id);
    }

    public void removeArticle(Article a) {
        articleDAO.delete(a);
    }

    public void addArticle(Article a) {
        articleDAO.add(a);
    }
}
