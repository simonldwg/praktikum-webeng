package com.shopmeowmeow.presentation.managedBeans;

import com.shopmeowmeow.businessLogic.managers.ShoppingCartManager;
import com.shopmeowmeow.transfer.Cat;
import com.shopmeowmeow.transfer.ShoppingCart;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named()
@SessionScoped
public class ShoppingCartBean implements Serializable {
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    private ShoppingCartManager shoppingCartManager = new ShoppingCartManager();

    private ShoppingCart shoppingCart = new ShoppingCart();

    public String addCat(Cat cat) {
        shoppingCartManager.addCat(cat, shoppingCart);
        return "cart.xhtml";
    }
    public String removeCat(Cat cat) {
        shoppingCartManager.removeCat(cat, shoppingCart);
        return "cart.xhtml";
    }

    public int getNumberOfCats() {
        return this.shoppingCart.getCats().size();
    }

    public float total() {
        return shoppingCartManager.total(this.shoppingCart);
    }
}
