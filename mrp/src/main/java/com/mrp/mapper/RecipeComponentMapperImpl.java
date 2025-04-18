package com.mrp.mapper;

import com.mrp.dto.RecipeComponentDTO;
import com.mrp.entities.RecipeComponent;

import org.springframework.stereotype.Component;

@Component
public class RecipeComponentMapperImpl implements RecipeComponentMapper {

    @Override
    public RecipeComponent toEntity(RecipeComponentDTO dto) {
        RecipeComponent entity = new RecipeComponent();
        entity.setPartName(dto.getPartName());
        entity.setQuantityPerBicycle(dto.getQuantityPerBicycle());
        entity.setSubComponent(dto.isSubComponent());
        entity.setParentPartName(dto.getParentPartName());
        return entity;
    }

    @Override
    public RecipeComponentDTO toDTO(RecipeComponent entity) {
        RecipeComponentDTO dto = new RecipeComponentDTO();
        dto.setPartName(entity.getPartName());
        dto.setQuantityPerBicycle(entity.getQuantityPerBicycle());
        dto.setSubComponent(entity.isSubComponent());
        dto.setParentPartName(entity.getParentPartName());
        return dto;
    }
}

