package com.shopmeowmeow.persistence.orm;

import com.shopmeowmeow.persistence.OrderDAO;
import com.shopmeowmeow.transfer.Cat;
import com.shopmeowmeow.transfer.Order;

import java.util.List;

public class OrmOrderDAO extends OrmDaoBase implements OrderDAO {
    @Override
    public List<Order> getAll() {
        return getDatabase().find(Order.class).findList();
    }

    @Override
    public void add(Order o) {
        getDatabase().insert(o);
    }
}
