package businessLogic;

import transfer.Article;
import transfer.ShoppingCart;

import java.util.HashMap;

public class ShoppingCartManager {

    private final ArticleManager articleManager;

    public ShoppingCartManager() {
        articleManager = new ArticleManager();
    }

    public boolean containsArticle(ShoppingCart s, Article a) {
        return s.getArticles().containsKey(a);
    }
    public void addToCart(ShoppingCart s, Article a, int quantity) {
        // Artikel aus dem ShoppingCart holen
        var articles = s.getArticles();
        // Artikelmenge in der Datenbank reduzieren
        articleManager.changeQuantityBy(a, -quantity);
        // Artikel dem Warenkorb hinzufügen
        articles.put(a, quantity);
        // Artikel im Warenkorb neu setzen
        s.setArticles(articles);
    }

    public void removeFromCart(ShoppingCart s, Article a) {
        var articles = s.getArticles();
        int articleQuantity = articles.get(a);
        articleManager.changeQuantityBy(a, +articleQuantity);
        articles.remove(a);
        s.setArticles(articles);
    }

    public float total(ShoppingCart s) {
        float total = 0.0F;
        for(var entry : s.getArticles().entrySet()) {
            var article = entry.getKey();
            int quantity = entry.getValue();
            total += quantity * article.getPrice();
        }
        s.setTotal(total);
        return total;
    }

    public void updateQuantity(ShoppingCart s, Article a, int newQuantity) {
        var articles = s.getArticles();
        int oldQuantity = articles.get(a);
        articleManager.changeQuantityBy(a, oldQuantity - newQuantity);
        articles.replace(a, newQuantity);
        s.setArticles(articles);
    }

    public float checkout(ShoppingCart s) {
        float total = this.total(s);
        s.setArticles(new HashMap<>());
        s.setTotal(0.0F);
        return total;
    }
    public void abortShoppingProcess(ShoppingCart s) {
        for(Article a : s.getArticles().keySet()) {
            articleManager.changeQuantityBy(a, s.getArticles().get(a));
        }
        s.setArticles(new HashMap<>());
        s.setTotal(0.0F);
    }
}
