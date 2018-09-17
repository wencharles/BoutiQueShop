package com.sopia.boutiqueshop.entity.services;

import com.sopia.boutiqueshop.entities.Order;

import java.util.List;

public interface OrdersService {

    public List<Order> getOrders();

    public void saveOrder(Order order);

    public Order getOrder(int OrderId);

    public void deleteOrder(int OrderOd);
}
