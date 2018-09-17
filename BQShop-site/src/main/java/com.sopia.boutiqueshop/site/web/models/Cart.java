/**
 * 
 */
package com.sopia.boutiqueshop.site.web.models;

import com.sopia.boutiqueshop.catalog.CatalogService;
import com.sopia.boutiqueshop.entities.Address;
import com.sopia.boutiqueshop.entities.Customer;
import com.sopia.boutiqueshop.entities.Payment;
import com.sopia.boutiqueshop.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Sopia Alfred on 6/19/2018 18:23 PM.
 * @project boutiqueshop.
 */

public class Cart {
	/*
	 * objects model to hold the cart data*/
	private List<LineItem> items;
	public Cart()
	{
		items = new ArrayList<LineItem>();

	}

    //item count
    public int getItemCount(){
        int count = 0;
        for (LineItem lineItem : items) {
            count += lineItem.getQuantity();
        }
        return count;
    }
	public void addItem(Product product)
	{
		for (LineItem lineItem : items)
		{
			if(lineItem.getProduct().getId().equals(product.getId())){
				lineItem.setQuantity(lineItem.getQuantity()+1);
				return;
			}
		}
		LineItem item = new LineItem(product, 1);
		this.items.add(item);
	}

	//upadte qaunity on the sinle row
	public void updateLineItemQuantity(Product product, int quantity){
		for (LineItem lineItem : items){
			if(lineItem.getProduct().getId().equals(product.getId())){
				lineItem.setQuantity(quantity);//set the new quanity value
			}
		}
	}

	public void deleteItem(Integer id){
			//cartItems.removeIf(t ->t.getProductId()==productId);
		LineItem item = null;
		for (LineItem lineItem : items)
		{
			if(lineItem.getProduct().getId().equals(id)){
				item = lineItem;
				break;
			}
		}
		if(item != null){
			items.remove(item);
		}
	}

    public BigDecimal getTotalAmount()
    {
        BigDecimal amount = new BigDecimal("0.0");
        for (LineItem lineItem : items)
        {
            amount = amount.add(lineItem.getSubTotal());
        }
        return amount;
    }

	public List<LineItem> getItems()
	{
		return items;
	}
	public void setItems(List<LineItem> items)
	{
		this.items = items;
	}

}
