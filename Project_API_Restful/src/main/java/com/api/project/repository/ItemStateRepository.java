package com.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.project.entity.ItemState;

public interface ItemStateRepository extends JpaRepository<ItemState, Integer>{

}
