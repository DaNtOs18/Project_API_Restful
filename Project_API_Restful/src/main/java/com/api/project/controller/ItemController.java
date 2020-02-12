package com.api.project.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.api.project.entity.Item;
import com.api.project.repository.ItemRepository;
import com.api.project.repository.ItemStateRepository;
import com.api.project.repository.SupplierRepository;

@RestController
public class ItemController {
	
	private final ItemRepository itemRepository;
	private final ItemStateRepository itemStateRepository;
	/* Sin usar todavía */
	private final SupplierRepository supplierRepository;

	ItemController(ItemRepository itemRepository,
			ItemStateRepository itemStateRepository,
			SupplierRepository supplierRepository) {
	    this.itemRepository = itemRepository;
	    this.itemStateRepository = itemStateRepository;
	    this.supplierRepository = supplierRepository;
	  }
	
	 @PostMapping("/item")
	  Item recordItem(@RequestBody Item item) {
		 itemStateRepository.save(item.getState());
		 Item itemRecorded = itemRepository.save(item);
		 return itemRecorded;
	  }
	 
	 @DeleteMapping("/item/{id}")
	  void deleteItem(@PathVariable int id) {
		 itemRepository.deleteById(id);
	  }

}
