package org.nttdata.spring.service;

import org.nttdata.spring.dto.MaterialDeSalaDTO;
import org.nttdata.spring.entity.Material;
import org.nttdata.spring.entity.MaterialDeSala;
import org.nttdata.spring.entity.SalaDeReunion;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.MaterialDeSalaMapper;
import org.nttdata.spring.repository.MaterialDeSalaRepository;
import org.nttdata.spring.repository.MaterialRepository;
import org.nttdata.spring.repository.SalaDeReunionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialDeSalaService {

    private final MaterialDeSalaRepository materialDeSalaRepository;
    private final SalaDeReunionRepository salaRepository;
    private final MaterialRepository materialRepository;

    public MaterialDeSalaService(MaterialDeSalaRepository materialDeSalaRepository,
                                 SalaDeReunionRepository salaRepository,
                                 MaterialRepository materialRepository) {
        this.materialDeSalaRepository = materialDeSalaRepository;
        this.salaRepository = salaRepository;
        this.materialRepository = materialRepository;
    }

    public MaterialDeSalaDTO create(MaterialDeSalaDTO dto) {

        SalaDeReunion sala = salaRepository.findById(dto.getSalaId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala no encontrada"));

        Material material = materialRepository.findById(dto.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado"));

        MaterialDeSala entity = new MaterialDeSala();
        entity.setSala(sala);
        entity.setMaterial(material);
        entity.setCantidad(dto.getCantidad());

        return MaterialDeSalaMapper.toDTO(materialDeSalaRepository.save(entity));
    }

    public MaterialDeSalaDTO update(Integer id, MaterialDeSalaDTO dto) {

        MaterialDeSala entity = materialDeSalaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material de sala no encontrado"));

        SalaDeReunion sala = salaRepository.findById(dto.getSalaId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala no encontrada"));

        Material material = materialRepository.findById(dto.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado"));

        entity.setSala(sala);
        entity.setMaterial(material);
        entity.setCantidad(dto.getCantidad());

        return MaterialDeSalaMapper.toDTO(materialDeSalaRepository.save(entity));
    }
}