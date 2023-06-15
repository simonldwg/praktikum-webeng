package transfer;

import java.util.HashMap;

public class ShoppingCart {
    private HashMap<Article, Integer> articles;
    private float total;
    public void setTotal(float total){
        this.total = total;
    }

    public float getTotal() {
        return this.total;
    }

    public ShoppingCart() {
        articles = new HashMap<>();
    }

    public HashMap<Article, Integer> getArticles() {
        return articles;
    }

    public void setArticles(HashMap<Article, Integer> articles) {
        this.articles = articles;
    }

    public String toString() {
        if(articles.isEmpty()) {
            return "Einkaufswagen ist leer!";
        }
        String output = "";
        for(var entry : articles.entrySet()) {
            output += entry.getKey().toString() + ", Menge im Warenkorb: " + entry.getValue() + "\n";
        }
        return output;
    }

}
