package com.mrp.service;

import com.mrp.entities.Inventory;
import com.mrp.entities.RecipeComponent;
import com.mrp.repositories.InventoryRepository;
import com.mrp.repositories.RecipeComponentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MRPServiceImpl implements MRPService {

    private final RecipeComponentRepository recipeRepo;
    private final InventoryRepository inventoryRepo;

    public MRPServiceImpl(RecipeComponentRepository recipeRepo, InventoryRepository inventoryRepo) {
        this.recipeRepo = recipeRepo;
        this.inventoryRepo = inventoryRepo;
    }

    @Override
    public Map<String, Integer> calculateTotalRequirement(int bicycles) {
        Map<String, Integer> total = new HashMap<>();
        List<RecipeComponent> baseComponents = recipeRepo.findByIsSubComponentFalse();

        for (RecipeComponent r : baseComponents) {
            int baseQty = r.getQuantityPerBicycle() * bicycles;
            List<RecipeComponent> subComponents = recipeRepo.findByParentPartName(r.getPartName());
            if (subComponents.isEmpty()) {
                total.merge(r.getPartName(), baseQty, Integer::sum);
            } else {
                for (RecipeComponent sub : subComponents) {
                    int requiredQty = sub.getQuantityPerBicycle() * baseQty;
                    total.merge(sub.getPartName(), requiredQty, Integer::sum);
                }
            }
        }
        return total;
    }

    @Override
    public Map<String, Integer> calculateProcurement(Map<String, Integer> required) {
        Map<String, Integer> toProcure = new HashMap<>();
        for (Map.Entry<String, Integer> entry : required.entrySet()) {
            int available = inventoryRepo.findById(entry.getKey()).map(Inventory::getQuantity).orElse(0);
            int needed = entry.getValue();
            toProcure.put(entry.getKey(), Math.max(0, needed - available));
        }
        return toProcure;
    }

    @Override
    public int calculateMaxBicyclesPossible() {
        List<RecipeComponent> baseComponents = recipeRepo.findByIsSubComponentFalse();
        int maxPossible = Integer.MAX_VALUE;

        for (RecipeComponent r : baseComponents) {
            int qtyPerBike = r.getQuantityPerBicycle();
            List<RecipeComponent> subComponents = recipeRepo.findByParentPartName(r.getPartName());
            if (subComponents.isEmpty()) {
                int available = inventoryRepo.findById(r.getPartName()).map(Inventory::getQuantity).orElse(0);
                maxPossible = Math.min(maxPossible, available / qtyPerBike);
            } else {
                for (RecipeComponent sub : subComponents) {
                    int needed = sub.getQuantityPerBicycle() * qtyPerBike;
                    int available = inventoryRepo.findById(sub.getPartName()).map(Inventory::getQuantity).orElse(0);
                    maxPossible = Math.min(maxPossible, available / needed);
                }
            }
        }
        return maxPossible;
    }

    @Override
    public void addInventory(String partName, int qty) {
        Inventory existing = inventoryRepo.findById(partName).orElse(new Inventory(partName, 0));
        existing.setQuantity(existing.getQuantity() + qty);
        inventoryRepo.save(existing);
    }

    @Override
    public void addPart(String partName) {
        if (!inventoryRepo.existsById(partName)) {
            inventoryRepo.save(new Inventory(partName, 0));
        }
    }

    @Override
    public void addRecipeComponent(RecipeComponent rc) {
        recipeRepo.save(rc);
    }
}