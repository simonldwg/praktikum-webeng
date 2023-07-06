package com.shopmeowmeow.presentation.listeners;

import com.shopmeowmeow.businessLogic.managers.ShoppingCartManager;
import com.shopmeowmeow.presentation.managedBeans.ShoppingCartBean;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        try {
            ShoppingCartBean bean = (ShoppingCartBean) session.getAttribute("shoppingCartBean");
            if(bean!=null) {
                bean.abortShoppingProcess();
            }
        } catch(ClassCastException exception) {
            System.err.println("ShoppingCartBean in gerade zerstörter Session ungültig");
        }
    }
}
