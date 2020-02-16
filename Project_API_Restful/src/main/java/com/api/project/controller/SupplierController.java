package com.api.project.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.project.entity.Supplier;
import com.api.project.repository.SupplierRepository;

@RestController
public class SupplierController {

	private final SupplierRepository supplierRepository;

	SupplierController(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@PostMapping("/supplier")
	void recordSupplier(@RequestBody Supplier supplier) {
		supplierRepository.save(supplier); 
	}

	@PutMapping("/supplier/{id}")
	void updateSupplier(@RequestBody Supplier supplier, @PathVariable int id) {
		Supplier supplierToUpdate = supplierRepository.getOne(id);
		supplierToUpdate.setName(supplier.getName());
		supplierToUpdate.setCountry(supplier.getCountry());
		supplierToUpdate.setItems(supplier.getItems());
		supplierRepository.save(supplierToUpdate);
	}

	@DeleteMapping("/supplier/{id}")
	void deleteSupplier(@PathVariable int id) {
		supplierRepository.deleteById(id);
	}
}
