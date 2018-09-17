/**
 * 
 */
package com.sopia.boutiqueshop.entities;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;

/**
 * @author Sopia  on  11:17 PM 18-Oct-17.
 * @project Online BoutiQue Shop
 */
@Entity
@Table(name = "categories")
//@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c")
//		, @NamedQuery(name = "Categories.findById", query = "SELECT c FROM Categories c WHERE c.id = :id")
//		, @NamedQuery(name = "Categories.findByName", query = "SELECT c FROM Categories c WHERE c.name = :name")
//		, @NamedQuery(name = "Categories.findByDispOrder", query = "SELECT c FROM Categories c WHERE c.dispOrder = :dispOrder")
//		, @NamedQuery(name = "Categories.findByDisabled", query = "SELECT c FROM Categories c WHERE c.disabled = :disabled")
//		, @NamedQuery(name = "Categories.findByDescription", query = "SELECT c FROM Categories c WHERE c.description = :description")})
public class 	Category
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(nullable=false, unique=true)
	@NotEmpty
	private String name;
	@Column(length=1024)
	private String description;
	@Column(name="disp_order")
	private Integer displayOrder;
	private boolean disabled;
	@OneToMany(mappedBy="category")
	private Set<Product> products;

	/*@CreatedDate
	private Date createdDate;*/
	/* to be tested for auditing*/
	/*@CreatedBy
	private String createdBy;

	@LastModifiedDate
	private Date lastModifiedDate;

	@LastModifiedBy
	private String lastModifiedBy;*/


	/*end of auditing*/

	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
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
	public Integer getDisplayOrder()
	{
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder)
	{
		this.displayOrder = displayOrder;
	}
	
	public boolean isDisabled()
	{
		return disabled;
	}
	public void setDisabled(boolean disabled)
	{
		this.disabled = disabled;
	}
	public Set<Product> getProducts()
	{
		return products;
	}
	public void setProducts(Set<Product> products)
	{
		this.products = products;
	}


	/*public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}*/

	/*public String getCreatedBy() {
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

//
}
