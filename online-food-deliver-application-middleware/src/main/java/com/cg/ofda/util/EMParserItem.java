package com.cg.ofda.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.ItemEntity;
import com.cg.ofda.model.ItemModel;
import com.cg.ofda.repository.ICartRepository;
import com.cg.ofda.repository.ICategoryRepository;

@Service
public class EMParserItem {
	
	/*
	 * EMParserFoodCart is Autowired
     */
	
	@Autowired
	private EMParserFoodCart cartParser;
	
	
	/*
	 * Category repository is autowired
	 * */	
	@Autowired
	private ICategoryRepository categoryRepo;
	
	
	
	/*
	 * Cart repository is autowired
	 * */	
	
	@Autowired
	private ICartRepository cartRepo;
	
	
	/*
	 * EMParserCategory is Autowired
     */
	
	@Autowired
	private EMParserCategory categoryParser;
	
	/*
	 * EMParserRestaurant is Autowired
     */
	
	@Autowired
	private EMParserRestaurant restaurantParser;
	
	/*
	 * Method to parse Model to Entity
     */
	
	
//	public EMParserItem() {
//		this.cartParser = new EMParserFoodCart();
//		this.categoryParser = new EMParserCategory();
//		this.restaurantParser = new EMParserRestaurant();	
//	}
//	
//	
//	
//	
//	public EMParserItem(EMParserFoodCart cartParser, EMParserCategory categoryParser,
//			EMParserRestaurant restaurantParser) {
//		super();
//		this.cartParser = new EMParserFoodCart();
//		this.categoryParser = new EMParserCategory();
//		this.restaurantParser = new EMParserRestaurant();
//	}




	public ItemEntity parse(ItemModel source) {
		return source == null ? null :
			new ItemEntity(source.getItemId(),
					source.getItemName(),
					categoryRepo.findById(source.getCatId()).orElse(null),
					source.getQuantity(),
					source.getCost(),
					cartRepo.findById(source.getCartId()).orElse(null));
	}
	
	/*
	 * Method to parse Entity to Model
     */
	
	public ItemModel parse(ItemEntity source) {
		return source == null ? null :
			new ItemModel(source.getItemId(),
					source.getItemName(),
					source.getCategory().getCatId(),
					source.getQuantity(),
					source.getCost(),
					source.getFoodCart().getCartId());
	}
	
	

}
