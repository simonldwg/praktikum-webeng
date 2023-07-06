package com.shopmeowmeow.transfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {
    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    private List<Cat> cats;

    public ShoppingCart() {
        cats = new ArrayList<>();
    }
    
}
