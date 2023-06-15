package presentation.web.listener;

import businessLogic.ShoppingCartManager;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import transfer.ShoppingCart;

@WebListener
public class UserSessionListener implements HttpSessionListener {
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        try {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if(cart!=null) {
                new ShoppingCartManager().abortShoppingProcess(cart);
            }
        } catch(ClassCastException exception) {
            System.err.println("Warenkorb in gerade zerstörter Session ungültig");
        }
    }
}
