package com.inventory.api.v1.activity.repository;

import com.inventory.api.v1.activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

	Iterable<Activity> findByActivityId(Integer activityId);
}
