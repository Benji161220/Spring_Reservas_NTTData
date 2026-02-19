package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.SalaDeReunionDTO;
import org.nttdata.spring.entity.SalaDeReunion;

public class SalaDeReunionMapper {

    private SalaDeReunionMapper() {}

    public static SalaDeReunionDTO toDTO(SalaDeReunion entity) {
        SalaDeReunionDTO dto = new SalaDeReunionDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setCapacidad(entity.getCapacidad());
        dto.setOficinaId(entity.getOficinaId());
        return dto;
    }

    public static SalaDeReunion toEntity(SalaDeReunionDTO dto) {
        SalaDeReunion entity = new SalaDeReunion();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setCapacidad(dto.getCapacidad());
        entity.setOficinaId(dto.getOficinaId());
        return entity;
    }
}
