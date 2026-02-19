package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.PlantaDTO;
import org.nttdata.spring.entity.Planta;

public class PlantaMapper {

    private PlantaMapper() {}

    public static PlantaDTO toDTO(Planta entity) {
        PlantaDTO dto = new PlantaDTO();
        dto.setId(entity.getId());
        dto.setOficinaId(entity.getOficinaId());
        dto.setNumero(entity.getNumero());
        return dto;
    }

    public static Planta toEntity(PlantaDTO dto) {
        Planta entity = new Planta();
        entity.setId(dto.getId());
        entity.setOficinaId(dto.getOficinaId());
        entity.setNumero(dto.getNumero());
        return entity;
    }
}
