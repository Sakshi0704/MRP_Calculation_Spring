package com.mrp.repositories;

import com.mrp.entities.RecipeComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeComponentRepository extends JpaRepository<RecipeComponent, Long> {
    List<RecipeComponent> findByParentPartName(String parentPartName);
    List<RecipeComponent> findByIsSubComponentFalse();
}
