package com.shopmeowmeow.persistence;

import com.shopmeowmeow.transfer.Cat;
import com.shopmeowmeow.transfer.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> getAll();

    void add(Order t);

}
