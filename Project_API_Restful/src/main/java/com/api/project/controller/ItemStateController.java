package com.api.project.controller;

import com.api.project.entity.ItemState;
import com.api.project.repository.ItemStateRepository;

public class ItemStateController {

	private final ItemStateRepository itemStateRepository;

	ItemStateController(ItemStateRepository itemStateRepository) {
	    this.itemStateRepository = itemStateRepository;
	  }

	  ItemState recordItemSate(ItemState itemState) {
		 itemStateRepository.save(itemState);
		 return itemStateRepository.save(itemState);
	  }
	 
}
