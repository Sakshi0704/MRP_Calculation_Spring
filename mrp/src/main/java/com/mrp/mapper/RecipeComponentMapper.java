package com.mrp.mapper;

import com.mrp.dto.RecipeComponentDTO;
import com.mrp.entities.RecipeComponent;

public interface RecipeComponentMapper {
    RecipeComponent toEntity(RecipeComponentDTO dto);
    RecipeComponentDTO toDTO(RecipeComponent entity);
}
