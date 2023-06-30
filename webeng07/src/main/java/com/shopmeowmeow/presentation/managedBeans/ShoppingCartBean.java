package com.shopmeowmeow.presentation.managedBeans;

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

    private ShoppingCart shoppingCart = new ShoppingCart();

    public void addCat(Cat cat) {
        var cats = this.shoppingCart.getCats();
        cats.add(cat);
        this.shoppingCart.setCats(cats);
    }
}
