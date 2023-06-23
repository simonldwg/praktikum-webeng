package com.shopmeowmeow.persistence.orm;

import com.shopmeowmeow.persistence.CatDAO;
import com.shopmeowmeow.transfer.Cat;

import java.util.List;

public class OrmCatDAO extends OrmDaoBase implements CatDAO {
    @Override
    public Cat get(long id) {
        return getDatabase().find(Cat.class, id);

    }

    @Override
    public List<Cat> getAll() {
        return getDatabase().find(Cat.class).findList();
    }

    @Override
    public void add(Cat cat) {
        getDatabase().insert(cat);
    }

    @Override
    public void update(Cat cat) {
        getDatabase().save(cat);

    }

    @Override
    public void delete(Cat cat) {

        getDatabase().delete(cat);
    }
    public boolean containsWithName(String name) {
        var filteredList = getDatabase().filter(Cat.class).eq("name", name).filter(this.getAll());
        return filteredList != null && !filteredList.isEmpty();
    }
}
