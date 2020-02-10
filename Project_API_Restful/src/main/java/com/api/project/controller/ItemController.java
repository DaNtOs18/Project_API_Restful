package com.api.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.project.entity.Item;
import com.api.project.repository.ItemRepository;
import com.api.project.repository.ItemStateRepository;

@RestController
@RequestMapping("/api")
public class ItemController {
	
	private final ItemRepository itemRepository;
	private final ItemStateController itemStateController;
	//private final ItemStateRepository itemStateRepository;

	ItemController(ItemRepository itemRepository, ItemStateController itemStateController) {
	    this.itemRepository = itemRepository;
	    //this.itemStateRepository = itemStateRepository;
	    this.itemStateController = itemStateController;
	  }
	
	 @PostMapping("/item")
	  Item recordItem(@RequestBody Item item) {
		 itemStateController.recordItemSate(item.getState());
		 return itemRepository.save(item);
	  }

}
