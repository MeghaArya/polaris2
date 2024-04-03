package com.inventory.api.v1.item.repository;

import com.inventory.api.v1.item.model.Item;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	Optional<Item> findBySkuName(String skuName);

}
