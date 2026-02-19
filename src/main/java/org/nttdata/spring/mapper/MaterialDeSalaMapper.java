package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.MaterialDeSalaDTO;
import org.nttdata.spring.entity.MaterialDeSala;

public class MaterialDeSalaMapper {

    private MaterialDeSalaMapper() {}

    public static MaterialDeSalaDTO toDTO(MaterialDeSala entity) {
        MaterialDeSalaDTO dto = new MaterialDeSalaDTO();
        dto.setId(entity.getId());
        dto.setSalaId(entity.getSalaId());
        dto.setMaterialId(entity.getMaterialId());
        dto.setCantidad(entity.getCantidad());
        return dto;
    }

    public static MaterialDeSala toEntity(MaterialDeSalaDTO dto) {
        MaterialDeSala entity = new MaterialDeSala();
        entity.setId(dto.getId());
        entity.setSalaId(dto.getSalaId());
        entity.setMaterialId(dto.getMaterialId());
        entity.setCantidad(dto.getCantidad());
        return entity;
    }
}
