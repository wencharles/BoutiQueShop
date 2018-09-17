/**
 * 
 */
package com.sopia.boutiqueshop.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Sopia  on  11:21 PM 18-Oct-17.
 * @project Online BoutiQue Shop
 */

@Entity
@Table(name = "payments")
//@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p")
//		, @NamedQuery(name = "Payments.findById", query = "SELECT p FROM Payments p WHERE p.id = :id")
//		, @NamedQuery(name = "Payments.findByCcNumber", query = "SELECT p FROM Payments p WHERE p.ccNumber = :ccNumber")
//		, @NamedQuery(name = "Payments.findByCvv", query = "SELECT p FROM Payments p WHERE p.cvv = :cvv")
//		, @NamedQuery(name = "Payments.findByAmount", query = "SELECT p FROM Payments p WHERE p.amount = :amount")})
public class Payment implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="cc_number")
	private String ccNumber;
	private String cvv;
	private BigDecimal amount;
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getCcNumber()
	{
		return ccNumber;
	}
	public void setCcNumber(String ccNumber)
	{
		this.ccNumber = ccNumber;
	}
	public String getCvv()
	{
		return cvv;
	}
	public void setCvv(String cvv)
	{
		this.cvv = cvv;
	}
	public BigDecimal getAmount()
	{
		return amount;
	}
	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}
		
}
