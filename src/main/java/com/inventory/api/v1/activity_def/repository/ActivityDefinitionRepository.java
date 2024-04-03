package com.inventory.api.v1.activity_def.repository;

import com.inventory.api.v1.activity_def.model.ActivityDefinition;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityDefinitionRepository extends JpaRepository<ActivityDefinition, Long> {
	Optional<ActivityDefinition> findByActivity(String activity);
}
