package com.inventory.api.v1.capacity.repository;

import com.inventory.api.v1.capacity.model.Capacity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacityRepository extends JpaRepository<Capacity, Long> {
}
