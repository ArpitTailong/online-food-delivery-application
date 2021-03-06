package com.cg.ofda.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FoodCartModel {

	/*
	 * All the private members are validate here with suitable datatypes
	 * 
	 */
	
	/*	To validate cartId cannot be null but can be empty*/
	@NotNull(message = "Food cart id cannot be null")
	private Long cartId;

	/*For validation of customer*/
	@Valid
	private Long customerId;

	/*	To validate itemList cannot be null and size>0*/
	@NotEmpty(message = " itemList cannot be empty")
	/*	To validate itemList cannot be null but can be empty*/
	@NotNull(message = " itemList cannot be omitted")
	private List<ItemModel> itemList;

	/*	To validate orderDetails cannot be null and size>0*/
	@NotEmpty(message = "OrderDetails cannot be empty")
	/*	To validate orderDetails cannot be null but can be empty*/
	@NotNull(message = "OrderDetails cannot be omitted")
	private OrderDetailsModel orderDetails;

	/*
	 * A default Constructor with no implementation
	 */

	public FoodCartModel() {
		// default
	}

	/*
	 * A Parameterized Constructor for assigning the values to private members
	 */


	public FoodCartModel(@NotNull(message = "Food cart id cannot be null") Long cartId, @Valid Long customerId) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
	}

	/*
	 * Corresponding Getters and Setters for private members
	 * 
	 */

	public Long getCartId() {
		return cartId;
	}


	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<ItemModel> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemModel> itemList) {
		this.itemList = itemList;
	}
	

	public OrderDetailsModel getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetailsModel orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
	/*
	 * Corresponding HashCode and Equals methods
	 * 
	 */


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((itemList == null) ? 0 : itemList.hashCode());
		result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
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
		FoodCartModel other = (FoodCartModel) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (itemList == null) {
			if (other.itemList != null)
				return false;
		} else if (!itemList.equals(other.itemList))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		return true;
	}

	



	
	/*
	 * toString() method overridden here
	 * 
	 */

	@Override
	public String toString() {
		return String.format("FoodCartModel [cartId=%s, customerId=%s, itemList=%s, orderDetails=%s]", cartId,
				customerId, itemList, orderDetails);
	}

	

}