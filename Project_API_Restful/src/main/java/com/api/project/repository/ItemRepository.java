package com.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.project.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
