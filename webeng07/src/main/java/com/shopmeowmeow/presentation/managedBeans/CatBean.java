package com.shopmeowmeow.presentation.managedBeans;

import com.shopmeowmeow.businessLogic.managers.CatManager;
import com.shopmeowmeow.transfer.Cat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.List;

@Named()
@ApplicationScoped
public class CatBean {
    private final CatManager catManager;

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    private List<Cat> cats;
    public CatBean() {
        this.catManager = new CatManager();
        this.cats = catManager.getAllCats();
    }

}
