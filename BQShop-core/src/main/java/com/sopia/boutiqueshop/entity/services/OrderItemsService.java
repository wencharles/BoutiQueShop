package com.sopia.boutiqueshop.entity.services;

import com.sopia.boutiqueshop.entities.OrderItem;

import java.util.List;

public interface OrderItemsService {

    public List<OrderItem> getOrderItems();

    public void saveOrderItems(OrderItem orderItems);

    public OrderItem getOrderItem(int OrderItemId);

    public void deleteOrderItem(int OrderItemId);
}
