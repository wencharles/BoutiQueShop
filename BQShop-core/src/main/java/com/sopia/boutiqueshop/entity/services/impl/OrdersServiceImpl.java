package com.sopia.boutiqueshop.entity.services.impl;

import com.sopia.boutiqueshop.entities.Order;
import com.sopia.boutiqueshop.entity.services.OrdersService;
import com.sopia.boutiqueshop.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<Order> getOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public void saveOrder(Order order) {
    ordersRepository.save(order);
    }

    @Override
    public Order getOrder(int OrderId) {
        return ordersRepository.getOne(OrderId);
    }

    @Override
    public void deleteOrder(int OrderOd) {
    ordersRepository.delete(OrderOd);
    }
}
