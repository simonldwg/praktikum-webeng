package com.shopmeowmeow.businessLogic.managers;

import com.shopmeowmeow.persistence.CatDAO;
import com.shopmeowmeow.persistence.DAOFactory;
import com.shopmeowmeow.transfer.Cat;

import java.util.List;

public class CatManager {
    private final CatDAO catDAO;
    public CatManager() {
        this.catDAO = DAOFactory.getCatDAO();
    }
    public List<Cat> getAllCats() {
        return catDAO.getAll();
    }

    public Cat getCat(long id) {
        return catDAO.get(id);
    }

    public void removeCat(Cat a) {
        catDAO.delete(a);
    }

    public void addCat(Cat a) {
        catDAO.add(a);
    }
}
