package com.inventory.api.v1.activity_def.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;



import com.inventory.api.v1.activity_def.model.ActivityDefinition;
import com.inventory.api.v1.activity_def.repository.ActivityDefinitionRepository;


@RestController
@RequestMapping("/api")
public class ActivityDefintionControllerV1 {
	
	@Autowired
	ActivityDefinitionRepository activityDefinitionRepository;
	
	@GetMapping("/activity_def")
	public ResponseEntity<List<ActivityDefinition>> getAllActivityDefinitions(@RequestParam(required = false) String activity) {
	    try {
	      List<ActivityDefinition> activityDefinitions = new ArrayList<ActivityDefinition>();

	      if (activity == null)
	    	  activityDefinitionRepository.findAll().forEach(activityDefinitions::add);
	      //else
	    	//  activityDefinitionRepository.findByActivity(activity).forEach(activityDefinitions::add);

	      if (activityDefinitions.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(activityDefinitions, HttpStatus.OK);
	    } 
	    catch (Exception e) {
	      System.out.print(e.toString());
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	//this allows us to search by specific ID number
	@GetMapping("/activity_def/{id}")
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
	
	 @PutMapping("/activity_def/{id}")
	    public ResponseEntity<ActivityDefinition> updateActivityDefinition(@PathVariable("id") long id, @RequestBody ActivityDefinition activityDefinitionDetails) {
	        Optional<ActivityDefinition> activityDefinitionData = activityDefinitionRepository.findById(id);

	        if (activityDefinitionData.isPresent()) {
	            ActivityDefinition activityDefinition = activityDefinitionData.get();
	            activityDefinition.setActivity(activityDefinitionDetails.getActivity());
	            activityDefinition.setDescription(activityDefinitionDetails.getDescription());
	            // Set other fields you wish to update, i am going to add in the date and time
	            activityDefinition.setPostedDateTime(LocalDateTime.now()); // Update the timestamp


	            return new ResponseEntity<>(activityDefinitionRepository.save(activityDefinition), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
//	 
	 @PostMapping("/activity_def")
	 public ResponseEntity<ActivityDefinition> createActivityDefinition(@RequestBody ActivityDefinition activityDefinition) {
		 //i am going to have it just automatically set to update posted time
		    activityDefinition.setPostedDateTime(LocalDateTime.now()); // Set the current date and time
	     try {
	         ActivityDefinition _activityDefinition = activityDefinitionRepository.save(activityDefinition);
	         return new ResponseEntity<>(_activityDefinition, HttpStatus.CREATED);
	     } catch (Exception e) {
	         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }
//	 
	//DELETE
	    @DeleteMapping("/activity_def/{id}")
	    public ResponseEntity<String> deleteActivityDefinition(@PathVariable("id") long id) {
	        try {
	            activityDefinitionRepository.deleteById(id);
	            return new ResponseEntity<>("Activity Definition with ID " + id + " has been deleted successfully", HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Failed to delete Activity Definition with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
//	
//	Post(add something new)
//	Delete
//	

}