package com.inventory.api.v1.capacity.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.api.v1.capacity.model.Capacity;
import com.inventory.api.v1.capacity.repository.CapacityRepository;

@RestController
public class CapacityControllerV1 {

    @Autowired
    CapacityRepository capacityRepository;

    @GetMapping("/api/capacity")
    public ResponseEntity<List<Capacity>> getAllCapacities() {
        try {
            List<Capacity> capacities = new ArrayList<>();
            capacityRepository.findAll().forEach(capacities::add);

            if (capacities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(capacities, HttpStatus.OK);
        } catch (Exception e) {
            System.out.print(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/capacity/{id}")
    public ResponseEntity<Capacity> getCapacityById(@PathVariable("id") long id) {
        try {
            Optional<Capacity> capacityOptional = capacityRepository.findById(id);
            return capacityOptional.map(capacity -> new ResponseEntity<>(capacity, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/capacity/{id}")
    public ResponseEntity<Capacity> updateCapacity(@PathVariable("id") long id, @RequestBody Capacity capacityDetails) {
        Optional<Capacity> capacityData = capacityRepository.findById(id);

        if (capacityData.isPresent()) {
            Capacity capacity = capacityData.get();
            capacity.setOrderMin(capacityDetails.getOrderMin());
            capacity.setWarehouseMin(capacityDetails.getWarehouseMin());
            capacity.setWarehouseMax(capacityDetails.getWarehouseMax());
            capacity.setFloorMin(capacityDetails.getFloorMin());
            capacity.setFloorMax(capacityDetails.getFloorMax());

            return new ResponseEntity<>(capacityRepository.save(capacity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/capacity")
    public ResponseEntity<?> createCapacity(@RequestBody Capacity capacity) {
        try {
            // Validate incoming data if necessary
            // Set the current timestamp (automatic)
            
            // Save the capacity
            Capacity _capacity = capacityRepository.save(capacity);
            return new ResponseEntity<>(_capacity, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while creating the capacity");
        }
    }

    @DeleteMapping("/api/capacity/{id}")
    public ResponseEntity<String> deleteCapacity(@PathVariable("id") long id) {
        try {
            capacityRepository.deleteById(id);
            return new ResponseEntity<>("Capacity with ID " + id + " has been deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete Capacity with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
