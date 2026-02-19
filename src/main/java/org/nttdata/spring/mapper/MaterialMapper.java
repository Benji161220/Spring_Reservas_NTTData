package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.MaterialDTO;
import org.nttdata.spring.entity.Material;

public class MaterialMapper {

    private MaterialMapper() {}

    public static MaterialDTO toDTO(Material entity) {
        MaterialDTO dto = new MaterialDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setCantidadTotal(entity.getCantidadTotal());
        return dto;
    }

    public static Material toEntity(MaterialDTO dto) {
        Material entity = new Material();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setCantidadTotal(dto.getCantidadTotal());
        return entity;
    }
}
