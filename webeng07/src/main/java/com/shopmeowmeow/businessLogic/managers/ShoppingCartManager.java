package com.shopmeowmeow.businessLogic.managers;

import com.shopmeowmeow.transfer.Cat;
import com.shopmeowmeow.transfer.ShoppingCart;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartManager {

    CatManager catManager;

    public ShoppingCartManager() {
        catManager = new CatManager();
    }
    public void addCat(Cat cat, ShoppingCart shoppingCart) {
        if(!cat.isReserved()) {
            var cats = shoppingCart.getCats();
            catManager.reserve(cat);
            cats.add(cat);
            shoppingCart.setCats(cats);
        }
    }

    public void removeCat(Cat cat, ShoppingCart shoppingCart) {
        var cats = shoppingCart.getCats();
        catManager.free(cat);
        cats.remove(cat);
        shoppingCart.setCats(cats);
    }

    public float total(ShoppingCart s) {
        float total = 0.0F;
        for(Cat c : s.getCats()) {
            total += c.getPrice();
        }
        return total;
    }

    public void abortShoppingProcess(ShoppingCart cart) {
        var cats = cart.getCats();
        for(Cat c : cats) {
            catManager.free(c);
        }
        cart.setCats(new ArrayList<>());
    }

}
