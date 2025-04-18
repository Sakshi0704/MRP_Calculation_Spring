package com.mrp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RecipeComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partName;
    private int quantityPerBicycle;
    private boolean isSubComponent;
    private String parentPartName;

    public RecipeComponent() {}
    public RecipeComponent(String partName, int quantityPerBicycle, boolean isSubComponent, String parentPartName) {
        this.partName = partName;
        this.quantityPerBicycle = quantityPerBicycle;
        this.isSubComponent = isSubComponent;
        this.parentPartName = parentPartName;
    }
    public Long getId() { return id; }
    public String getPartName() { return partName; }
    public int getQuantityPerBicycle() { return quantityPerBicycle; }
    public boolean isSubComponent() { return isSubComponent; }
    public String getParentPartName() { return parentPartName; }

    public void setPartName(String partName) { this.partName = partName; }
    public void setQuantityPerBicycle(int quantityPerBicycle) { this.quantityPerBicycle = quantityPerBicycle; }
    public void setSubComponent(boolean subComponent) { isSubComponent = subComponent; }
    public void setParentPartName(String parentPartName) { this.parentPartName = parentPartName; }
}