/**
 * 
 */
package com.inventory.api.v1.activity_def.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.api.v1.activity_def.model.ActivityDefinition;


/**
 * Activity Definition Repository Interface
 */
public interface ActivityDefinitionRepository extends JpaRepository<ActivityDefinition, Long> {
	
	/**
	 * Find activities given a date
	 * @param published
	 * @return list of ActivityDefinition
	 */
	//List<ActivityDefinition> findByPostedDate(LocalDateTime published);
	
	/**
	 * Find activities given an activity code
	 * @param activity
	 * @return list of activities
	 */
	//List<ActivityDefinition> findByActivity(String activity);
}
