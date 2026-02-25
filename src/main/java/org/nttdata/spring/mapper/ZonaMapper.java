package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.ZonaDTO;
import org.nttdata.spring.entity.Planta;
import org.nttdata.spring.entity.Zona;

public class ZonaMapper {

    private ZonaMapper() {
    }

    public static ZonaDTO toDTO(Zona entity) {
        ZonaDTO dto = new ZonaDTO();
        dto.setId(entity.getId());
        dto.setIdPlanta(entity.getPlanta() != null ? entity.getPlanta().getId() : null);
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public static Zona toEntity(ZonaDTO dto) {
        Zona entity = new Zona();
        entity.setId(dto.getId());
        if (dto.getIdPlanta() != null) {
            Planta planta = new Planta();
            planta.setId(dto.getIdPlanta());
            entity.setPlanta(planta);
        }
        entity.setNombre(dto.getNombre());
        return entity;
    }
}
