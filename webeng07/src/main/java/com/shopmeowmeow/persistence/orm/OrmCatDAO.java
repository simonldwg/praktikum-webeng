package com.shopmeowmeow.persistence.orm;

import com.shopmeowmeow.model.CatColor;
import com.shopmeowmeow.persistence.CatDAO;
import com.shopmeowmeow.transfer.Cat;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
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
    public List<Cat> getMostRecent() {
        return getDatabase().find(Cat.class).where().gt("addedAt", new Date(ZonedDateTime.of(LocalDateTime.now().minusDays(30), ZoneId.systemDefault()).toInstant().toEpochMilli())).order("added_at DESC").findList();
    }

    @Override
    public List<Cat> getCatsWhereEquals(String attribute, Object value) {
        System.out.println(value);
        return getDatabase().find(Cat.class).where().eq(attribute, value).findList();
    }

}
