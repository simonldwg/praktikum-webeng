package presentation.web;

import businessLogic.ArticleManager;
import businessLogic.InvalidQuantityException;
import businessLogic.ShoppingCartManager;
import configuration.Configuration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import transfer.Article;
import transfer.ShoppingCart;

import java.io.IOException;

@WebServlet("/")
public class FrontController extends HttpServlet {
    private final ArticleManager articleManager = new ArticleManager();
    private final ShoppingCartManager shoppingCartManager = new ShoppingCartManager();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if(action == null) {
            response.sendRedirect(response.encodeRedirectURL("/webeng05/?action=articles"));
            return;
        }
        ShoppingCart cart;
        try {
            cart = (ShoppingCart) session.getAttribute("cart");
        } catch(ClassCastException e) {
            cart = new ShoppingCart();
            session.invalidate();
            session = request.getSession(true);
            session.setAttribute("cart", cart);
        }
        if(cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        switch(action) {
            case "articles":
                //Artikelübersicht anzeigen
                //Lesen Sie alle Artikel über die BusinessLogic aus und übergeben Sie an eine JSP Seite
                final var allArticles = articleManager.getAllArticles();
                request.setAttribute("allArticles", allArticles);
                request.getRequestDispatcher("/WEB-INF/articles.jsp").forward(request, response);
                break;
            case "detail":
                // Detailseite eines Artikels anzeigen
                long articleID = 0L;
                try {
                    articleID = Long.parseLong(request.getParameter("id"));
                } catch(NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                final Article article = articleManager.getArticle(articleID);
                if(article == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                request.setAttribute("article", article);
                request.getRequestDispatcher("/WEB-INF/article-detail.jsp").forward(request, response);
                break;
            case "cart":
                shoppingCartManager.total(cart);
                request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
                break;
            case "checkout":
                request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect(response.encodeRedirectURL("/webeng05/?action=articles"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String action = request.getParameter("action");
        HttpSession session = request.getSession();
        ShoppingCart cart;
        try {
            cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
            }
        } catch (ClassCastException e) {
            session.invalidate();
            session = request.getSession(true);
            cart = new ShoppingCart();
        }
        if("addToCart".equals(action)) {
            long articleID = 0L;
            int quantity = 0;
            System.out.println(request.getParameter("id"));
            System.out.println(request.getParameter("quantity"));
            try {
                articleID = Long.parseLong(request.getParameter("id"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            final Article article = articleManager.getArticle(articleID);
            if (article == null || quantity > 100 || quantity < 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            try {
                if (shoppingCartManager.containsArticle(cart, article)) {
                    shoppingCartManager.updateQuantity(cart, article, quantity);
                } else {
                    shoppingCartManager.addToCart(cart, article, quantity);
                }
            } catch(InvalidQuantityException e) {
                response.getWriter().println("Die Menge, die zum Warenkorb hinzugefuegt werden soll, darf nicht groesser als der aktuell verfuegbare Lagerbestand sein.");
                return;
            }
            shoppingCartManager.total(cart);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/?action=cart"));
        } else if("cancel".equals(action)) {
            shoppingCartManager.abortShoppingProcess(cart);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/?action=articles"));
        } else if("checkout-finish".equals(action)) {
            shoppingCartManager.checkout(cart);
            request.getRequestDispatcher("/WEB-INF/checkout-finish.jsp").forward(request, response);
        }
        session.setAttribute("cart", cart);
    }
}
