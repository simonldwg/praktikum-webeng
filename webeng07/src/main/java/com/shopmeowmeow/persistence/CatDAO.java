package com.shopmeowmeow.persistence;

import com.shopmeowmeow.transfer.Cat;

import java.util.List;

public interface CatDAO {
    Cat get(long id);

    List<Cat> getAll();

    void add(Cat t);

    void update(Cat t);

    void delete(Cat t);

    boolean containsWithName(String name);
}
