package com.sopia.boutiqueshop.admin.web.models;

import com.sopia.boutiqueshop.entities.OrderItem;
import com.sopia.boutiqueshop.entities.Product;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sopia Alfred on 7/13/2018 2:21 AM.
 * @project boutiqueshop.
 */
public class ItemOrdered {

    private List<OrderItem> items;
    //private int quantity;

    public ItemOrdered()
    {
        items = new ArrayList<OrderItem>();

    }
    public BigDecimal getTotalAmount()
    {
        BigDecimal amount = new BigDecimal("0.0");
        for (OrderItem orderItem : items)
        {
            amount = amount.add(orderItem.getSubTotal());
        }
        return amount;
    }
}
