/**
 * 
 */
package com.sopia.boutiqueshop.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author Sopia  on  11:19 PM 18-Oct-17.
 * @project Online BoutiQue Shop
 */
@Entity
@Table(name = "order_items")
//@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "OrderItems.findAll", query = "SELECT o FROM OrderItems o")
//		, @NamedQuery(name = "OrderItems.findById", query = "SELECT o FROM OrderItems o WHERE o.id = :id")
//		, @NamedQuery(name = "OrderItems.findByQuantity", query = "SELECT o FROM OrderItems o WHERE o.quantity = :quantity")
//		, @NamedQuery(name = "OrderItems.findByPrice", query = "SELECT o FROM OrderItems o WHERE o.price = :price")})
public class OrderItem implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	private BigDecimal price;
	private int quantity;
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	public Order getOrder()
	{
		return order;
	}
	public void setOrder(Order order)
	{
		this.order = order;
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
		return product.getPrice().multiply(new BigDecimal(quantity));
	}
	
}
