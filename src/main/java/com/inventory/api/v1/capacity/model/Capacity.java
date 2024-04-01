package com.inventory.api.v1.capacity.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "capacity")
public class Capacity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "order_min")
    private int orderMin;

    @Column(name = "warehouse_min")
    private int warehouseMin;

    @Column(name = "warehouse_max")
    private int warehouseMax;

    @Column(name = "floor_min")
    private int floorMin;

    @Column(name = "floor_max")
    private int floorMax;

    @Column(name = "time")
    private LocalDateTime time;

    public Capacity() {
    }

    public Capacity(int itemId, int orderMin, int warehouseMin, int warehouseMax, int floorMin, int floorMax, LocalDateTime time) {
        this.itemId = itemId;
        this.orderMin = orderMin;
        this.warehouseMin = warehouseMin;
        this.warehouseMax = warehouseMax;
        this.floorMin = floorMin;
        this.floorMax = floorMax;
        this.time = time;
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

    public int getOrderMin() {
        return orderMin;
    }

    public void setOrderMin(int orderMin) {
        this.orderMin = orderMin;
    }

    public int getWarehouseMin() {
        return warehouseMin;
    }

    public void setWarehouseMin(int warehouseMin) {
        this.warehouseMin = warehouseMin;
    }

    public int getWarehouseMax() {
        return warehouseMax;
    }

    public void setWarehouseMax(int warehouseMax) {
        this.warehouseMax = warehouseMax;
    }

    public int getFloorMin() {
        return floorMin;
    }

    public void setFloorMin(int floorMin) {
        this.floorMin = floorMin;
    }

    public int getFloorMax() {
        return floorMax;
    }

    public void setFloorMax(int floorMax) {
        this.floorMax = floorMax;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capacity capacity = (Capacity) o;
        return Objects.equals(id, capacity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
