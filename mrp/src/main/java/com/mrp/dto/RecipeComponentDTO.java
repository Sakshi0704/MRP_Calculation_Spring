package com.mrp.dto;

public class RecipeComponentDTO {
    private String productName;
    private String partName;
    private int quantityPerBicycle;
    private boolean isSubComponent;
    private String parentPartName;

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getQuantityPerBicycle() {
        return quantityPerBicycle;
    }

    public void setQuantityPerBicycle(int quantityPerBicycle) {
        this.quantityPerBicycle = quantityPerBicycle;
    }

    public boolean isSubComponent() {
        return isSubComponent;
    }

    public void setSubComponent(boolean subComponent) {
        isSubComponent = subComponent;
    }

    public String getParentPartName() {
        return parentPartName;
    }

    public void setParentPartName(String parentPartName) {
        this.parentPartName = parentPartName;
    }
}
