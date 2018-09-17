/**
 * 
 */
package com.sopia.boutiqueshop.site.web.models;

import com.sopia.boutiqueshop.entities.Product;

import java.math.BigDecimal;

/**
 * @author Sopia Alfred on 6/19/2018 22:43 PM.
 * @project boutiqueshop.
 */

public class LineItem
{

	/*
	 * objectss model to hold in one single row data in the cart page*/
	private Product product;
	private int quantity;


	public LineItem()
	{
	}

	public LineItem(Product product, int quantity)
	{
		this.product = product;
		this.quantity = quantity;
		//this.lineItemTotal = product.getPrice();
	}

	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public BigDecimal getSubTotal()
	{
		// price*qunatity
		return product.getPrice().multiply(new BigDecimal(quantity)); //calculates the total amount in a single row in cart pg
	}


}
