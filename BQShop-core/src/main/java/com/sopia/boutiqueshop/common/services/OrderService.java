package com.sopia.boutiqueshop.common.services;

import com.sopia.boutiqueshop.entities.Order;
import com.sopia.boutiqueshop.entities.OrderItem;
import com.sopia.boutiqueshop.entity.services.OrdersService;
import com.sopia.boutiqueshop.repositories.OrderItemsRepository;
import com.sopia.boutiqueshop.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sopia Alfred on 6/10/2018 4:40 PM.
 * @project boutiqueshop.
 */
@Service
@Transactional
public class OrderService
{
    private static final BQLogger logger = BQLogger.getLogger(OrderService.class);

    @Autowired
    EmailService emailService;
    @Autowired
    OrdersRepository orderRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;

    public Order createOrder(Order order)
    {
        //order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderNumber(String.valueOf(System.currentTimeMillis()));
        Order savedOrder = orderRepository.save(order);
        logger.info("New order created. Order Number : {}", savedOrder.getOrderNumber());
        return savedOrder;
    }

    public Order getOrder(String orderNumber)
    {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    public List<Order> getAllOrders()
    {
        Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
        return orderRepository.findAll(sort);
    }

    public Order updateOrder(Order order) {
        Order o = getOrder(order.getOrderNumber());
        o.setStatus(order.getStatus());
        Order savedOrder = orderRepository.save(o);
        return savedOrder;
    }


    public List <OrderItem> getAllOrderItmes(){
        return orderItemsRepository.findAll();
    }


}
