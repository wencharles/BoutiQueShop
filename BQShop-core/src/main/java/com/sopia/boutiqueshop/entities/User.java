/**
 * 
 */
package com.sopia.boutiqueshop.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Sopia  on  11:25 PM 18-Oct-17.
 * @project Online BoutiQue Shop
 */

@Entity
@Table(name = "users")
//@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
//		, @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id")
//		, @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name")
//		, @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
//		, @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
//		, @NamedQuery(name = "Users.findByPasswordResetToken", query = "SELECT u FROM Users u WHERE u.passwordResetToken = :passwordResetToken")})
public class User
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(nullable=false)
	@NotEmpty()
	private String name;
	@Column(nullable=false, unique=true)
	@NotEmpty
	@Email(message="{errors.invalid_email}")
	private String email;
	@Column(nullable=false)
	@NotEmpty
	@Size(min=4)
	private String password;
	private String passwordResetToken;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
	      name="user_role",
	      joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
	      inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	private List<Role> roles;
	
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
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public List<Role> getRoles()
	{
		return roles;
	}
	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}
	public String getPasswordResetToken()
	{
		return passwordResetToken;
	}
	public void setPasswordResetToken(String passwordResetToken)
	{
		this.passwordResetToken = passwordResetToken;
	}
	
	
}
