package com.inventory.api.v1.inventory_counts.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "inventory_counts")
public class InventoryCounts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "ware")
    private int ware;

    @Column(name = "floor")
    private int floor;

    @Column(name = "returned")
    private int returned;

    @Column(name = "damage")
    private int damage;

    @Column(name = "time")
    private LocalDateTime timestamp;

    public InventoryCounts() {
    }

    public InventoryCounts(int itemId, int ware, int floor, int returned, int damage, LocalDateTime timestamp) {
        this.itemId = itemId;
        this.ware = ware;
        this.floor = floor;
        this.returned = returned;
        this.damage = damage;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getWare() {
        return ware;
    }

    public void setWare(int ware) {
        this.ware = ware;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryCounts that = (InventoryCounts) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
