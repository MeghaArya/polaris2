package com.inventory.api.v1.activity_def.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.api.v1.activity_def.model.ActivityDefinition;
import com.inventory.api.v1.activity_def.repository.ActivityDefinitionRepository;

@RestController
public class ActivityDefintionControllerV1 {

    @Autowired
    ActivityDefinitionRepository activityDefinitionRepository;

    @GetMapping("/api/activity_def")
    public ResponseEntity<List<ActivityDefinition>> getAllActivityDefinitions(@RequestParam(required = false) String activity) {
        try {
            List<ActivityDefinition> activityDefinitions = new ArrayList<>();

            if (activity == null)
                activityDefinitionRepository.findAll().forEach(activityDefinitions::add);

            if (activityDefinitions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(activityDefinitions, HttpStatus.OK);
        } catch (Exception e) {
            System.out.print(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/activity_def/{id}")
    public ResponseEntity<ActivityDefinition> getActivityById(@PathVariable("id") long id) {
        try {
            Optional<ActivityDefinition> activityDefinitionOptional = activityDefinitionRepository.findById(id);
            return activityDefinitionOptional.map(activityDefinition -> new ResponseEntity<>(activityDefinition, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/activity_def/{id}")
    public ResponseEntity<ActivityDefinition> updateActivityDefinition(@PathVariable("id") long id, @RequestBody ActivityDefinition activityDefinitionDetails) {
        Optional<ActivityDefinition> activityDefinitionData = activityDefinitionRepository.findById(id);

        if (activityDefinitionData.isPresent()) {
            ActivityDefinition activityDefinition = activityDefinitionData.get();
            activityDefinition.setActivity(activityDefinitionDetails.getActivity());
            activityDefinition.setDescription(activityDefinitionDetails.getDescription());
            activityDefinition.setPostedDateTime(LocalDateTime.now()); // Update the timestamp

            return new ResponseEntity<>(activityDefinitionRepository.save(activityDefinition), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/activity_def")
    public ResponseEntity<?> createActivityDefinition(@RequestBody ActivityDefinition activityDefinition) {
        try {
            // Validate incoming data
            if (activityDefinition.getActivity() == null || activityDefinition.getActivity().isEmpty()) {
                return ResponseEntity.badRequest().body("Activity name is required");
            }

            // Set the current date and time
            activityDefinition.setPostedDateTime(LocalDateTime.now());

            // Save the activity definition
            ActivityDefinition _activityDefinition = activityDefinitionRepository.save(activityDefinition);
            return new ResponseEntity<>(_activityDefinition, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while creating the activity definition");
        }
    }


    @DeleteMapping("/api/activity_def/{id}")
    public ResponseEntity<String> deleteActivityDefinition(@PathVariable("id") long id) {
        try {
            activityDefinitionRepository.deleteById(id);
            return new ResponseEntity<>("Activity Definition with ID " + id + " has been deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete Activity Definition with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
