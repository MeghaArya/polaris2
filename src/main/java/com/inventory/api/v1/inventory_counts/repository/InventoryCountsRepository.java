package com.inventory.api.v1.inventory_counts.repository;

import com.inventory.api.v1.inventory_counts.model.InventoryCounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryCountsRepository extends JpaRepository<InventoryCounts, Long> {
}
