package com.inventory.api.v1.activity.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inventory.api.v1.activity.model.Activity;
import com.inventory.api.v1.activity.repository.ActivityRepository;

@RestController
@RequestMapping("/api/activity")
public class ActivityControllerV1 {

    @Autowired
    ActivityRepository activityRepository;

    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities(@RequestParam(required = false) Integer activityId) {
        try {
            List<Activity> activities = new ArrayList<>();

            if (activityId == null) {
                activityRepository.findAll().forEach(activities::add);
            } else {
                activityRepository.findByActivityId(activityId).forEach(activities::add);
            }

            if (activities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(activities, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") long id) {
        try {
            Optional<Activity> activityOptional = activityRepository.findById(id);
            return activityOptional.map(activity -> new ResponseEntity<>(activity, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable("id") long id, @RequestBody Activity activityDetails) {
        try {
            Optional<Activity> activityData = activityRepository.findById(id);
            if (activityData.isPresent()) {
                Activity activity = activityData.get();
                activity.setItemId(activityDetails.getItemId());
                activity.setActivityId(activityDetails.getActivityId());
                activity.setQuantity(activityDetails.getQuantity());
                activity.setTime(new Timestamp(System.currentTimeMillis()));
                return new ResponseEntity<>(activityRepository.save(activity), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createActivity(@RequestBody Activity activity) {
        try {
            if (activity.getActivityId() == null || activity.getActivityId() <= 0 || activity.getQuantity() <= 0) {
                return ResponseEntity.badRequest().body("Invalid activity details");
            }

            activity.setTime(new Timestamp(System.currentTimeMillis()));
            Activity _activity = activityRepository.save(activity);
            return new ResponseEntity<>(_activity, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while creating the activity");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable("id") long id) {
        try {
            activityRepository.deleteById(id);
            return new ResponseEntity<>("Activity with ID " + id + " has been deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete Activity with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
