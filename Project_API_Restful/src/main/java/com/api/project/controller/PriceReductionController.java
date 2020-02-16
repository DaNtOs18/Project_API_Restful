package com.api.project.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.project.entity.PriceReduction;
import com.api.project.repository.PriceReductionRepository;

@RestController
public class PriceReductionController {
	private final PriceReductionRepository priceReductionRepository;

	PriceReductionController(PriceReductionRepository priceReductionRepository) {
		this.priceReductionRepository = priceReductionRepository;
	}

	@PostMapping("/item/price_reduction")
	void recordPriceReduction(@RequestBody PriceReduction priceReduction) {
		priceReductionRepository.save(priceReduction); 
	}

	@PutMapping("/item/price_reduction/{id}")
	void updatePriceReduction(@RequestBody PriceReduction priceReduction, @PathVariable int id) {
		PriceReduction priceReductionToUpdate = priceReductionRepository.getOne(id);
		priceReductionToUpdate.setReducedPrice(priceReduction.getReducedPrice());
		priceReductionToUpdate.setStartDate(priceReduction.getStartDate());
		priceReductionToUpdate.setEndDate(priceReduction.getEndDate());
		priceReductionRepository.save(priceReductionToUpdate);
	}

	@DeleteMapping("/item/price_reduction/{id}")
	void deletePriceReduction(@PathVariable int id) {
		priceReductionRepository.deleteById(id);
	}

}
