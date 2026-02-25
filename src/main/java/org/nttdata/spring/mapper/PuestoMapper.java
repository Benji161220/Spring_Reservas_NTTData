package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.PuestoDTO;
import org.nttdata.spring.entity.Puesto;
import org.nttdata.spring.entity.Zona;

public class PuestoMapper {

    private PuestoMapper() {
    }

    public static PuestoDTO toDTO(Puesto entity) {
        PuestoDTO dto = new PuestoDTO();
        dto.setId(entity.getId());
        dto.setIdZona(entity.getZona() != null ? entity.getZona().getId() : null);
        dto.setNombre(entity.getNombre());
        dto.setCodigo(entity.getCodigo());
        return dto;
    }

    public static Puesto toEntity(PuestoDTO dto) {
        Puesto entity = new Puesto();
        entity.setId(dto.getId());
        if (dto.getIdZona() != null) {
            Zona zona = new Zona();
            zona.setId(dto.getIdZona());
            entity.setZona(zona);
        }
        entity.setNombre(dto.getNombre());
        entity.setCodigo(dto.getCodigo());
        return entity;
    }
}