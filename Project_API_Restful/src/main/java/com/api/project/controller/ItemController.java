package com.api.project.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.api.project.entity.Item;
import com.api.project.entity.ItemState;
import com.api.project.repository.ItemRepository;
import com.api.project.repository.ItemStateRepository;

@RestController
public class ItemController {
	
	private final ItemRepository itemRepository;
	private final ItemStateRepository itemStateRepository;

	ItemController(ItemRepository itemRepository,
			ItemStateRepository itemStateRepository) {
	    this.itemRepository = itemRepository;
	    this.itemStateRepository = itemStateRepository;
	  }
	
	 @PostMapping("/item")
	  void recordItem(@RequestBody Item item) {
		 itemStateRepository.save(item.getState());
		 itemRepository.save(item);
	  }
	 
	 @PutMapping("/item/{id}")
	  void updateItem(@RequestBody Item item, @PathVariable int id) {
		 Item itemToUpdate = itemRepository.getOne(id);
		 ItemState itemStateToUpdate = itemStateRepository.getOne(itemToUpdate.getState().getId());
		 itemStateToUpdate.setIsActive(item.getState().getIsActive());
		 itemStateToUpdate.setReason(item.getState().getReason());
		 itemStateToUpdate.setChangedBy(item.getState().getChangedBy());
		 itemToUpdate.setDescription(item.getDescription());
		 itemToUpdate.setPrice(item.getPrice());
		 itemToUpdate.setState(itemStateToUpdate);
		 itemToUpdate.setSuppliers(item.getSuppliers());
		 /* These should be not modified */
         //itemToUpdate.setCreationDate(item.getCreationDate());
         //itemToUpdate.setCreatedBy(item.getCreatedBy());
		 itemRepository.save(itemToUpdate);
	 }
	 
	 /* Admin */
	 @DeleteMapping("/item/{id}")
	  void deleteItem(@PathVariable int id) {
		 itemRepository.deleteById(id);
	  }
	 
}
