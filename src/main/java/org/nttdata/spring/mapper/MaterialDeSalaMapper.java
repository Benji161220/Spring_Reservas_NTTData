package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.MaterialDeSalaDTO;
import org.nttdata.spring.entity.Material;
import org.nttdata.spring.entity.MaterialDeSala;
import org.nttdata.spring.entity.SalaDeReunion;

public class MaterialDeSalaMapper {

    private MaterialDeSalaMapper() {
    }

    public static MaterialDeSalaDTO toDTO(MaterialDeSala entity) {
        MaterialDeSalaDTO dto = new MaterialDeSalaDTO();
        dto.setId(entity.getId());
        dto.setSalaId(entity.getSala() != null ? entity.getSala().getId() : null);
        dto.setMaterialId(entity.getMaterial() != null ? entity.getMaterial().getId() : null);
        dto.setCantidad(entity.getCantidad());
        return dto;
    }

    public static MaterialDeSala toEntity(MaterialDeSalaDTO dto) {
        MaterialDeSala entity = new MaterialDeSala();
        entity.setId(dto.getId());
        if (dto.getSalaId() != null) {
            SalaDeReunion sala = new SalaDeReunion();
            sala.setId(dto.getSalaId());
            entity.setSala(sala);
        }
        if (dto.getMaterialId() != null) {
            Material material = new Material();
            material.setId(dto.getMaterialId());
            entity.setMaterial(material);
        }
        entity.setCantidad(dto.getCantidad());
        return entity;
    }
}
