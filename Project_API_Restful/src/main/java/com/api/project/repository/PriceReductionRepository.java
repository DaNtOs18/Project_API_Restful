package com.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.project.entity.PriceReduction;

public interface PriceReductionRepository extends JpaRepository<PriceReduction, Integer> {

}