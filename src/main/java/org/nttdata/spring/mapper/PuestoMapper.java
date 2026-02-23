package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.PuestoDTO;
import org.nttdata.spring.entity.Puesto;

public class PuestoMapper {

    private PuestoMapper() {}

    public static PuestoDTO toDTO(Puesto entity) {
        PuestoDTO dto = new PuestoDTO();
        dto.setId(entity.getId());
        dto.setIdZona(entity.getIdZona());
        dto.setNombre(entity.getNombre());
        dto.setCodigo(entity.getCodigo());
        return dto;
    }

    public static Puesto toEntity(PuestoDTO dto) {
        Puesto entity = new Puesto();
        entity.setId(dto.getId());
        entity.setIdZona(dto.getIdZona());
        entity.setNombre(dto.getNombre());
        entity.setCodigo(dto.getCodigo());
        return entity;
    }
}