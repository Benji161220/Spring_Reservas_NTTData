package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.OficinaDTO;
import org.nttdata.spring.entity.Oficina;

public class OficinaMapper {

    private OficinaMapper() {}

    public static OficinaDTO toDTO(Oficina entity) {
        OficinaDTO dto = new OficinaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public static Oficina toEntity(OficinaDTO dto) {
        Oficina entity = new Oficina();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        return entity;
    }
}
