package com.shopmeowmeow.businessLogic.managers;

import com.shopmeowmeow.persistence.DAOFactory;
import com.shopmeowmeow.persistence.OrderDAO;
import com.shopmeowmeow.transfer.Cat;
import com.shopmeowmeow.transfer.Order;

import java.util.List;

public class OrderManager {
    private OrderDAO orderDAO;
    public OrderManager() {
        orderDAO = DAOFactory.getOrderDAO();
    }
    public void addOrder(Order o) {
        orderDAO.add(o);
    }
    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    public void addCatsToOrder(Order o, List<Cat> cats) {
        String catString = "";
        for(Cat c: cats) {
            catString += c.getName() + ", ";
        }
        catString = catString.substring(0, catString.length() - 2);
        o.setCats(catString);
    }
}
