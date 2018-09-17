/**
 * 
 */
package com.sopia.boutiqueshop.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Sopia  on  11:18 PM 18-Oct-17.
 * @project Online BoutiQue Shop
 */

@Entity
@Table(name = "customers")
//@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c")
//		, @NamedQuery(name = "Customers.findById", query = "SELECT c FROM Customers c WHERE c.id = :id")
//		, @NamedQuery(name = "Customers.findByFirstname", query = "SELECT c FROM Customers c WHERE c.firstname = :firstname")
//		, @NamedQuery(name = "Customers.findByLastname", query = "SELECT c FROM Customers c WHERE c.lastname = :lastname")
//		, @NamedQuery(name = "Customers.findByEmail", query = "SELECT c FROM Customers c WHERE c.email = :email")
//		, @NamedQuery(name = "Customers.findByPhone", query = "SELECT c FROM Customers c WHERE c.phone = :phone")
//		, @NamedQuery(name = "Customers.findByPassword", query = "SELECT c FROM Customers c WHERE c.password = :password")})
public class Customer implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="firstname", nullable=false)
	@NotEmpty
	private String firstName;
	@Column(name="lastname")
	private String lastName;
	//@NotEmpty
	@Email
	@Column(name="email")
	private String email;
	@NotEmpty
	@Column(name="password", nullable=false)
	private String password;
	private String phone;
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
