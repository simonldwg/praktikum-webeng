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
    private float total;
    public void setTotal(float total){
        this.total = total;
    }

    public float getTotal() {
        return this.total;
    }

    public ShoppingCart() {
        cats = new ArrayList<>();
    }
    
}
