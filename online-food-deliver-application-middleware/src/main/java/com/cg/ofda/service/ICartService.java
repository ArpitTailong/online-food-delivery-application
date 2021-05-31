package com.cg.ofda.service;

import java.util.List;

import com.cg.ofda.exception.CartException;
import com.cg.ofda.exception.CustomerException;
import com.cg.ofda.exception.ItemException;
import com.cg.ofda.model.FoodCartModel;
import com.cg.ofda.model.ItemModel;

public interface ICartService {

	
	/* Definition of addCart for adding cart*/
	public FoodCartModel addCart(FoodCartModel cart) throws CartException;
	
	/* Definition of addItemsToCart By adding items to cart */
	public FoodCartModel addItemsToCart(FoodCartModel cart, ItemModel item) throws CartException, ItemException;
	
	
	/* Definition of updateCart for updating cart */
	public FoodCartModel updateCart(FoodCartModel cart) throws CartException;
	
//	public FoodCartModel updateItemsInCart(FoodCartModel cart, List<ItemModel> item);
	

	
	/* Definition of removeItem method for removing items from FoodCart */
	public boolean removeCart(Long cartId) throws CartException;
	
	
	/*Definition of view cart method for viewing a cart using cartId */
	public FoodCartModel viewCart(Long cartId) throws CartException;
	
	/*Definition of cart method for viewing all carts */
	public List<FoodCartModel> viewAllCarts() throws CartException;

	/* Definition of clearCart method for clearing FoodCart */
	public FoodCartModel clearCart(FoodCartModel cart) throws CartException;
	
	/* Definition of getCartByCustomer for getting cart of a customer  */
	public FoodCartModel getCartByCustomer(Long customerId) throws CartException, CustomerException;
	
	
	

}