package com.cg.ofda.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.RestaurantEntity;
import com.cg.ofda.model.RestaurantModel;
import com.cg.ofda.repository.IItemRepository;
import com.cg.ofda.repository.IRestaurantRepository;

@Service
public class EMParserRestaurant {
	
	/*
	 * Bill Repository is Autowired
     */
	
	@Autowired
	private IRestaurantRepository billRepo;
	

	/*
	 * Item Repository is Autowired
     */
	
	@Autowired
	private IItemRepository itemRepo;
	
	/*
	 * EMParserItem is Autowired
     */
	
	@Autowired
	private EMParserItem itemParser;
	
	
//	
//	public EMParserRestaurant() {
//		this.itemParser = itemParser;
//
//	}
//	
//	public EMParserRestaurant(IRestaurantRepository billRepo, EMParserItem itemParser) {
//		super();
//		this.billRepo = billRepo;
//		this.itemParser = itemParser;
//	}
//	
	
	
	/*
	 * Method to parse Entity to Model
     */


	public RestaurantModel parse(RestaurantEntity source) {
		return source==null ? null:
			new RestaurantModel (source.getRestaurantId(),
					source.getRestaurantName(),
					source.getAddress(),
					source.getItems().getItemId(),
					source.getManagerName(),
					source.getContactNumber());
					
	}
	
	/*
	 * Method to parse Model to Entity
     */
	
	public RestaurantEntity parse(RestaurantModel source) {
		return source==null ? null:
			new RestaurantEntity (source.getRestaurantId(),
					source.getRestaurantName(),
					source.getAddress(),
					itemRepo.findById(source.getItemId()).orElse(null),
					source.getManagerName(),
					source.getContactNumber());
	}
	

	/*
	 * Method to parse Model Restaurant to Entity Restaurant
     */
	

	public List<RestaurantEntity> parse(List<RestaurantModel> list){
		
		List<RestaurantEntity> rlist =new ArrayList<>();
		for(RestaurantModel model : list) {
			rlist.add(parse(model));
		}
		return rlist;
	}
	

	/*
	 * Method to parse Entity Restaurant to Model Restaurant
     */
	
	public List<RestaurantModel> parseEntity(List<RestaurantEntity> list){
		
		List<RestaurantModel> rlist =new ArrayList<>();
		for(RestaurantEntity entity : list) {
			rlist.add(parse(entity));
		}
		return rlist;
	}
}
