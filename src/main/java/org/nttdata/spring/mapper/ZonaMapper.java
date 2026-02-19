package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.ZonaDTO;
import org.nttdata.spring.entity.Zona;

public class ZonaMapper {

    private ZonaMapper() {}

    public static ZonaDTO toDTO(Zona entity) {
        ZonaDTO dto = new ZonaDTO();
        dto.setId(entity.getId());
        dto.setIdPlanta(entity.getIdPlanta());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public static Zona toEntity(ZonaDTO dto) {
        Zona entity = new Zona();
        entity.setId(dto.getId());
        entity.setIdPlanta(dto.getIdPlanta());
        entity.setNombre(dto.getNombre());
        return entity;
    }
}
