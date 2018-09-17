package com.sopia.boutiqueshop.entity.services.impl;

import com.sopia.boutiqueshop.entities.OrderItem;
import com.sopia.boutiqueshop.entity.services.OrderItemsService;
import com.sopia.boutiqueshop.repositories.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderItemsServiceImpl implements OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Override
    public List<OrderItem> getOrderItems() {
        return orderItemsRepository.findAll();
    }

    @Override
    public void saveOrderItems(OrderItem orderItems) {
        orderItemsRepository.save(orderItems);
    }

    @Override
    public OrderItem getOrderItem(int OrderItemId) {
        return orderItemsRepository.getOne(OrderItemId);
    }

    @Override
    public void deleteOrderItem(int OrderItemId) {
    orderItemsRepository.delete(OrderItemId);
    }
}
