package com.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.project.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}