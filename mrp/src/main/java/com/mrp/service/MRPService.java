package com.mrp.service;

import com.mrp.dto.RecipeComponentDTO;
import com.mrp.entities.RecipeComponent;

import java.util.Map;

public interface MRPService {

    Map<String, Integer> calculateTotalRequirement(int bicycles);

    Map<String, Integer> calculateProcurement(Map<String, Integer> required);

    int calculateMaxBicyclesPossible();

    void addInventory(String partName, int qty);

    void addPart(String partName);

    void addRecipeComponent(RecipeComponentDTO rc);

}
