package com.mrp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Part {
    @Id
    private String name;

    public Part() {}
    public Part(String name) {
        this.name = name;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

