package com.inventory.api.v1.item.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.api.v1.item.model.Item;
import com.inventory.api.v1.item.repository.ItemRepository;

@RestController
public class ItemControllerV1 {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/api/item")
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) String department) {
        try {
            List<Item> items = new ArrayList<>();

            if (department == null)
                itemRepository.findAll().forEach(items::add);

            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            System.out.print(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Integer id) {
        try {
            Optional<Item> itemOptional = itemRepository.findById(id);
            return itemOptional.map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/item/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable("id") Integer id, @RequestBody Item itemDetails) {
        Optional<Item> itemData = itemRepository.findById(id);

        if (itemData.isPresent()) {
            Item item = itemData.get();
            item.setQuantity(itemDetails.getQuantity());
            item.setDepartment(itemDetails.getDepartment());
            item.setSubdepartment(itemDetails.getSubdepartment());
            item.setStoreId(itemDetails.getStoreId());
            item.setSkuName(itemDetails.getSkuName());
            item.setSkuDescription(itemDetails.getSkuDescription());
            item.setUpcId(itemDetails.getUpcId());
            return new ResponseEntity<>(itemRepository.save(item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/item")
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        try {
            // Validate incoming data
            if (item.getDepartment() == null || item.getDepartment().isEmpty()) {
                return ResponseEntity.badRequest().body("Department name is required");
            }

            // Save the item
            Item _item = itemRepository.save(item);
            return new ResponseEntity<>(_item, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while creating the item");
        }
    }

    @DeleteMapping("/api/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Integer id) {
        try {
            itemRepository.deleteById(id);
            return new ResponseEntity<>("Item with ID " + id + " has been deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete Item with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
