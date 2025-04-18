package com.mrp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Inventory {
    @Id
    private String partName;
    private int quantity;

    public Inventory() {}
    public Inventory(String partName, int quantity) {
        this.partName = partName;
        this.quantity = quantity;
    }
    public String getPartName() { return partName; }
    public void setPartName(String partName) { this.partName = partName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
