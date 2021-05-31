package com.cg.ofda.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/* This is an Entity class
 * 
 * 
 */
@Entity
/*To create table "order_datails"*/
@Table(name = "order_details")
public class OrderDetailsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * All the private members are defined here with suitable datatypes
	 * 
	 */
	@Id
	/*To create column order_id*/
	@Column(name = "order_id")
	@GeneratedValue
	private Long orderId;
	
	/*To create column order_date*/
	@Column(name = "order_date")
	private LocalDate orderDate;

	/*Specifying OneToOne relationship*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cart_id")
	private FoodCartEntity cart;

	/*To create column order_status*/
	@Column(name = "order_status", length = 50)
	private String orderStatus;

	/*Specifying OneToOne relationship*/
	@OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
	private BillEntity bill;

	/*
	 * A default Constructor with no implementation
	 */
	public OrderDetailsEntity() {
		// default
	}

	/*
	 * A Parameterized Constructor for assigning the values to private members
	 */

	public OrderDetailsEntity(Long orderId,LocalDate orderDate, FoodCartEntity cart, String orderStatus) {
		super();
		this.orderId=orderId;
		this.orderDate = orderDate;
		this.cart = cart;
		this.orderStatus = orderStatus;
	}

	/*
	 * Corresponding Getters and Setters for private members
	 * 
	 */

	public Long getOrderId() {
		return orderId;
	}
	

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public FoodCartEntity getCart() {
		return cart;
	}

	public void setCart(FoodCartEntity cart) {
		this.cart = cart;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	

	public BillEntity getBill() {
		return bill;
	}

	public void setBill(BillEntity bill) {
		this.bill = bill;
	}
	
	/* HashCode and Equals*/
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bill == null) ? 0 : bill.hashCode());
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsEntity other = (OrderDetailsEntity) obj;
		if (bill == null) {
			if (other.bill != null)
				return false;
		} else if (!bill.equals(other.bill))
			return false;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		return true;
	}

	/*
	 * toString() method overridden here
	 * 
	 */
	@Override
	public String toString() {
		return String.format("OrderDetails [orderId=%s, orderDate=%s, orderStatus=%s]", orderId, orderDate,
				orderStatus);
	}

}
