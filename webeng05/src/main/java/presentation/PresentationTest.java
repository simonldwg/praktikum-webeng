package presentation;

import businessLogic.ArticleManager;
import businessLogic.ShoppingCartManager;
import configuration.Configuration;
import transfer.Article;
import transfer.ShoppingCart;


public class PresentationTest {
    public static void main(String[] args) {
        try {
            Configuration.setDataSourceType("ORM");
            Configuration.setUsername("sa");
            Configuration.setPassword("sa");
            Configuration.setUrl("jdbc:h2:./h2/db");
            Configuration.setDriver("org.h2.Driver");

            var articleManager = new ArticleManager();

            System.out.println("""
                        --------------------------
                        Alle Artikel löschen
                        --------------------------
                    """);
            for(Article article : articleManager.getAllArticles()) {
                articleManager.removeArticle(article);
            }
            System.out.println("Erfolgreich gelöscht");


            System.out.println("""
                        --------------------------
                        Testartikel einfügen
                        --------------------------
                    """);
            var a1 = new Article(0,"TestArtikel01",10,3);
            var a2 = new Article(0,"TestArtikel02",20,5);
            articleManager.addArticle(a1);
            articleManager.addArticle(a2);
            articleManager.addArticle(new Article(0,"TestArtikel03",30,7));
            articleManager.addArticle(new Article(0,"TestArtikel04",40,4));
            articleManager.addArticle(new Article(0,"TestArtikel05",50,2));
            articleManager.addArticle(new Article(0,"TestArtikel06",60,6));

            for(Article article : articleManager.getAllArticles()) {
                System.out.println(article);
            }

            var cart = new ShoppingCart();

            System.out.println("""
                        --------------------------
                        Artikel zum Warenkorb hinzufügen
                        --------------------------
                    """);
            System.out.println("Aktueller Warenkorb:");
            System.out.println(cart);
            ShoppingCartManager shoppingCartManager = new ShoppingCartManager();
            shoppingCartManager.addToCart(cart, a1, 1);
            shoppingCartManager.addToCart(cart, a2, 2);
            System.out.println("Neuer Warenkorb:");
            System.out.println(cart);
            System.out.println("---\nGesamtbetrag: " + shoppingCartManager.total(cart) + "€");

            System.out.println("""
                        --------------------------
                        aktuelle Artikelliste
                        --------------------------
                    """);
            for(Article article : articleManager.getAllArticles()) {
                System.out.println(article);
            }

            shoppingCartManager.checkout(cart);
            System.out.println("""
                        --------------------------
                        Warenkorb nach Checkout
                        --------------------------
                    """);
            System.out.println(cart);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
