package com.cg.ofda.service;

import java.util.List;

import com.cg.ofda.exception.ItemException;
import com.cg.ofda.exception.OrderException;
import com.cg.ofda.exception.RestaurantException;
import com.cg.ofda.model.ItemModel;

public interface IItemService {

	/* definition of addItem method for adding new item */
	public ItemModel addItem(ItemModel item) throws ItemException;

	/* definition of viewItem method for viewing item */
	public ItemModel viewItem(Long id) throws ItemException;

	/* definition of updateItem method for updating Item */
	public ItemModel updateItem(ItemModel item) throws ItemException;

	/* definition of removeItem method for removing Item */
	public boolean removeItem(Long id) throws ItemException;

	/* definition of viewAllItems method for viewing all items */
	public List<ItemModel> viewAllItems() throws ItemException;
	
	/* definition of viewAllItems method for viewing all items in a restaurant */
	public ItemModel viewItemByRestaurant(String restaurantName) throws ItemException, RestaurantException;
	
	
	/* definition of viewItemsByOrder for viewing all items in an order */
	public List<ItemModel> viewItemsByOrder(Long orderId) throws ItemException , OrderException;

}
