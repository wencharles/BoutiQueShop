/**
 * 
 */
package com.sopia.boutiqueshop.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Sopia  on  11:06 PM 18-Oct-17.
 * @project Online BoutiQue Shop
 */
@Entity
@Table(name="addresses")
//@Table(name = "addresses")
//@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "Addresses.findAll", query = "SELECT a FROM Addresses a")
//		, @NamedQuery(name = "Addresses.findById", query = "SELECT a FROM Addresses a WHERE a.id = :id")
//		, @NamedQuery(name = "Addresses.findByAddressLine1", query = "SELECT a FROM Addresses a WHERE a.addressLine1 = :addressLine1")
//		, @NamedQuery(name = "Addresses.findByAddressLine2", query = "SELECT a FROM Addresses a WHERE a.addressLine2 = :addressLine2")
//		, @NamedQuery(name = "Addresses.findByCity", query = "SELECT a FROM Addresses a WHERE a.city = :city")
//		, @NamedQuery(name = "Addresses.findByState", query = "SELECT a FROM Addresses a WHERE a.state = :state")
//		, @NamedQuery(name = "Addresses.findByZipCode", query = "SELECT a FROM Addresses a WHERE a.zipCode = :zipCode")
//		, @NamedQuery(name = "Addresses.findByCountry", query = "SELECT a FROM Addresses a WHERE a.country = :country")})
public class Address implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getAddressLine1()
	{
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2()
	{
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}
	public String getZipCode()
	{
		return zipCode;
	}
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}
	
}
