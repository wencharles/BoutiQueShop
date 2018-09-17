/**
 * 
 */
package com.sopia.boutiqueshop.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Sopia  on  11:23 PM 18-Oct-17.
 * @project Online BoutiQue Shop
 */

@Entity
@Table(name = "products")
//@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")
//		, @NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id")
//		, @NamedQuery(name = "Products.findBySku", query = "SELECT p FROM Products p WHERE p.sku = :sku")
//		, @NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name")
//		, @NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description = :description")
//		, @NamedQuery(name = "Products.findByImageUrl", query = "SELECT p FROM Products p WHERE p.imageUrl = :imageUrl")
//		, @NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price")
//		, @NamedQuery(name = "Products.findByDisabled", query = "SELECT p FROM Products p WHERE p.disabled = :disabled")
//		, @NamedQuery(name = "Products.findByCreatedOn", query = "SELECT p FROM Products p WHERE p.createdOn = :createdOn")})


@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(nullable=false, unique=true)
	private String sku;
	@Column(nullable=false)
	private String name;
	private String description;
	private String quantity;
	@Column(nullable=false)
	private BigDecimal price = new BigDecimal("0.0");
	private String imageUrl;
	private boolean disabled;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name="created_on")
	private Date createdOn = new Date();

	/* to be tested for auditing*//*
	@CreatedBy
	private String createdBy;

	@LastModifiedDate
	private Date lastModifiedDate;

	@LastModifiedBy
	private String lastModifiedBy;


	*//*end of auditing*/
	
	@ManyToOne
	@JoinColumn(name="cat_id")
	private Category category;
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getSku()
	{
		return sku;
	}
	public void setSku(String sku)
	{
		this.sku = sku;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getQuantity()
	{
		return quantity;
	}
	public void setQuantity(String quantity)
	{
		this.quantity = quantity;
	}

	public BigDecimal getPrice()
	{
		return price;
	}
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	public String getImageUrl()
	{
		return imageUrl;
	}
	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public boolean isDisabled()
	{
		return disabled;
	}
	public void setDisabled(boolean disabled)
	{
		this.disabled = disabled;
	}
	public Date getCreatedOn()
	{
		return createdOn;
	}
	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}
	
	public Category getCategory()
	{
		return category;
	}
	public void setCategory(Category category)
	{
		this.category = category;
	}




	//settters and getters of auditing
   /* public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }*/
}
