package com.cg.ofda.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.FoodCartEntity;
import com.cg.ofda.entity.ItemEntity;
import com.cg.ofda.entity.OrderDetailsEntity;
import com.cg.ofda.entity.RestaurantEntity;
import com.cg.ofda.exception.ItemException;
import com.cg.ofda.exception.OrderException;
import com.cg.ofda.exception.RestaurantException;
import com.cg.ofda.model.ItemModel;
import com.cg.ofda.repository.IItemRepository;
import com.cg.ofda.repository.IOrderRepository;
import com.cg.ofda.repository.IRestaurantRepository;
import com.cg.ofda.util.EMParserItem;

@Service
public class ItemServiceImpl implements IItemService {
	
	public static final String NOT_FOUND = "no item with id #";
	public static final String PRESENT = " present";
	
	/*
	 * Item Repository is Autowired 
     */

	@Autowired
	private IItemRepository itemRepo;
	
	/*
	 * Restaurant Repository is Autowired 
     */
	
	@Autowired
	private IRestaurantRepository resRepo;
	
	/*
	 * Order Repository is Autowired 
     */
	@Autowired
	private IOrderRepository orderRepo;
	
	
	/*
	 * EMParserItem is Autowired 
     */

	@Autowired
	private EMParserItem parser;
	
	/*
	 * Default constructor
     */

	public ItemServiceImpl() {
		this.parser = new EMParserItem();
	}

	/*Parameterized constructor for assigning
	 * */
	public ItemServiceImpl(IItemRepository itemRepo) {
		super();
		this.itemRepo = itemRepo;
		this.parser = new EMParserItem();
	}

	/*
	 * Implementation of addItem method to add Item
	 */

	@Transactional
	@Override
	public ItemModel addItem(ItemModel item) throws ItemException {
		if (item != null) {
			if (itemRepo.existsById(item.getItemId())) {
				throw new ItemException("Item with this id already exists");
			}

			item = parser.parse(itemRepo.save(parser.parse(item)));
		}

		return item;
	}

	/*
	 * Implementation of viewItem method to view Item
	 */

	@Override
	public ItemModel viewItem(Long id) throws ItemException {
		ItemEntity item= itemRepo.findById(id).orElse(null);
		if(item==null)
			throw new ItemException(NOT_FOUND + id + PRESENT);
		else
			return parser.parse(item);
	}

	/*
	 * Implementation of updateItem method to update Item
	 */

	@Transactional
	@Override
	public ItemModel updateItem(ItemModel item) throws ItemException {
		ItemEntity oldItem = itemRepo.findById(item.getItemId()).orElse(null);
		if (oldItem == null) {
			throw new ItemException(NOT_FOUND + item.getItemId() + PRESENT);
		} else {
			item = parser.parse(itemRepo.save(parser.parse(item)));
		}
		return item;
	}

	/*
	 * Implementation of removeItem method to remove Item
	 */

	@Transactional
	@Override
	public boolean removeItem(Long id) throws ItemException {
		ItemEntity oldItem = itemRepo.findById(id).orElse(null);
		boolean isDeleted = false;
		if (oldItem == null) {
			throw new ItemException(NOT_FOUND + id + PRESENT);
		} else {
			itemRepo.deleteById(id);
			isDeleted = true;
		}
		return isDeleted;
	}

	/*
	 * Implementation of viewAllItems method to view all Items
	 */
	@Override
	public List<ItemModel> viewAllItems() throws ItemException {

		return itemRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());

	}

	@Override
	public ItemModel viewItemByRestaurant(String restaurantName) throws ItemException, RestaurantException {
		
		RestaurantEntity rest= resRepo.findByRestaurantName(restaurantName);
		
		if(rest == null) {
			throw new RestaurantException("no restaurant with " + restaurantName + " present");
		}
			ItemEntity item = rest.getItems();
		return parser.parse(item);
	}

	@Override
	public List<ItemModel> viewItemsByOrder(Long orderId) throws ItemException, OrderException {
		
		OrderDetailsEntity order = orderRepo.findById(orderId).orElse(null);
		
		if(order == null)
			throw new OrderException("No Order with id #"+orderId +" present");
		
		FoodCartEntity cart= order.getCart();
		
		List<ItemEntity> items= cart.getItemList();
		
		return items.stream().map(parser :: parse).collect(Collectors.toList());
	}

}