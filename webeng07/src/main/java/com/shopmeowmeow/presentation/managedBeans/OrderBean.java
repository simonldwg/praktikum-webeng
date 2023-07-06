package com.shopmeowmeow.presentation.managedBeans;

import com.shopmeowmeow.businessLogic.managers.CatManager;
import com.shopmeowmeow.businessLogic.managers.OrderManager;
import com.shopmeowmeow.model.OrderPaymentMethod;
import com.shopmeowmeow.transfer.Cat;
import com.shopmeowmeow.transfer.Order;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named()
@SessionScoped
public class OrderBean implements Serializable {
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private Order order;
    @Inject
    private ShoppingCartBean shoppingCartBean;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    private List<Order> orders;
    private final OrderManager orderManager;

    private final CatManager catManager;

    public OrderBean() {
        super();
        this.orderManager = new OrderManager();
        this.orders = this.orderManager.getAllOrders();
        this.catManager = new CatManager();
    }
    public String prepareAddOrder() {
        this.order = new Order();
        return "goToCheckout";
    }

    public String addOrder(){
        List<Cat> catsInThisOrder = shoppingCartBean.getShoppingCart().getCats();
        this.orderManager.addCatsToOrder(order, catsInThisOrder);
        this.orderManager.addOrder(order);
        this.catManager.deleteCats(catsInThisOrder);
        this.shoppingCartBean.clearCart();
        this.orders.add(order);
        this.order = new Order();
        return "success.xhtml";
    }
    public List<OrderPaymentMethod> getPaymentMethods() {
        return Arrays.asList(OrderPaymentMethod.values());
    }


}
