package com.inventory.api.v1.inventory_counts.controller;

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

import com.inventory.api.v1.inventory_counts.model.InventoryCounts;
import com.inventory.api.v1.inventory_counts.repository.InventoryCountsRepository;

@RestController
public class InventoryCountsControllerV1 {

    @Autowired
    InventoryCountsRepository inventoryCountsRepository;

    @GetMapping("/api/inventory_counts")
    public ResponseEntity<List<InventoryCounts>> getAllInventoryCounts() {
        try {
            List<InventoryCounts> inventoryCounts = new ArrayList<>();
            inventoryCountsRepository.findAll().forEach(inventoryCounts::add);
            if (inventoryCounts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inventoryCounts, HttpStatus.OK);
        } catch (Exception e) {
            System.out.print(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/inventory_counts/{id}")
    public ResponseEntity<InventoryCounts> getInventoryCountById(@PathVariable("id") long id) {
        try {
            Optional<InventoryCounts> inventoryCountOptional = inventoryCountsRepository.findById(id);
            return inventoryCountOptional.map(inventoryCount -> new ResponseEntity<>(inventoryCount, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/inventory_counts/{id}")
    public ResponseEntity<InventoryCounts> updateInventoryCount(@PathVariable("id") long id, @RequestBody InventoryCounts inventoryCountDetails) {
        Optional<InventoryCounts> inventoryCountData = inventoryCountsRepository.findById(id);
        if (inventoryCountData.isPresent()) {
            InventoryCounts inventoryCount = inventoryCountData.get();
            inventoryCount.setItemId(inventoryCountDetails.getItemId());
            inventoryCount.setWare(inventoryCountDetails.getWare());
            inventoryCount.setFloor(inventoryCountDetails.getFloor());
            inventoryCount.setReturned(inventoryCountDetails.getReturned());
            inventoryCount.setDamage(inventoryCountDetails.getDamage());
            inventoryCount.setTimestamp(LocalDateTime.now()); // Update the timestamp
            return new ResponseEntity<>(inventoryCountsRepository.save(inventoryCount), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/inventory_counts")
    public ResponseEntity<?> createInventoryCount(@RequestBody InventoryCounts inventoryCount) {
        try {
            // Set the current date and time
            inventoryCount.setTimestamp(LocalDateTime.now());
            // Save the inventory count
            InventoryCounts _inventoryCount = inventoryCountsRepository.save(inventoryCount);
            return new ResponseEntity<>(_inventoryCount, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while creating the inventory count");
        }
    }

    @DeleteMapping("/api/inventory_counts/{id}")
    public ResponseEntity<String> deleteInventoryCount(@PathVariable("id") long id) {
        try {
            inventoryCountsRepository.deleteById(id);
            return new ResponseEntity<>("Inventory Count with ID " + id + " has been deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete Inventory Count with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
