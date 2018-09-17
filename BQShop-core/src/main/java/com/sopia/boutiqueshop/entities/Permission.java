/**
 * 
 */
package com.sopia.boutiqueshop.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Sopia  on  11:22 PM 18-Oct-17.
 * @project Online BoutiQue Shop
 */
@Entity
@Table(name = "permissions")
//@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "Permissions.findAll", query = "SELECT p FROM Permissions p")
//		, @NamedQuery(name = "Permissions.findById", query = "SELECT p FROM Permissions p WHERE p.id = :id")
//		, @NamedQuery(name = "Permissions.findByName", query = "SELECT p FROM Permissions p WHERE p.name = :name")
//		, @NamedQuery(name = "Permissions.findByDescription", query = "SELECT p FROM Permissions p WHERE p.description = :description")})
public class Permission
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(nullable=false, unique=true)
	private String name;
	@Column(length=1024)
	private String description;
	@ManyToMany(mappedBy="permissions")
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
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}


}