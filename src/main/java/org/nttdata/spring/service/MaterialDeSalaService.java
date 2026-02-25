package org.nttdata.spring.service;

import org.nttdata.spring.dto.MaterialDeSalaDTO;
import org.nttdata.spring.entity.MaterialDeSala;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.MaterialDeSalaMapper;
import org.nttdata.spring.repository.MaterialDeSalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialDeSalaService {

    private final MaterialDeSalaRepository materialDeSalaRepository;

    public MaterialDeSalaService(MaterialDeSalaRepository materialDeSalaRepository) {
        this.materialDeSalaRepository = materialDeSalaRepository;
    }

    public List<MaterialDeSalaDTO> findAll() {
        return materialDeSalaRepository.findAll().stream()
                .map(MaterialDeSalaMapper::toDTO)
                .toList();
    }

    public MaterialDeSalaDTO findById(Integer id) {
        MaterialDeSala materialDeSala = materialDeSalaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material de sala no encontrado con id: " + id));
        return MaterialDeSalaMapper.toDTO(materialDeSala);
    }

    public List<MaterialDeSalaDTO> findBySalaId(Integer salaId) {
        return materialDeSalaRepository.findBySalaId(salaId).stream()
                .map(MaterialDeSalaMapper::toDTO)
                .toList();
    }

    public MaterialDeSalaDTO create(MaterialDeSalaDTO dto) {
        MaterialDeSala materialDeSala = MaterialDeSalaMapper.toEntity(dto);
        materialDeSala.setId(null);
        return MaterialDeSalaMapper.toDTO(materialDeSalaRepository.save(materialDeSala));
    }

    public MaterialDeSalaDTO update(Integer id, MaterialDeSalaDTO dto) {
        MaterialDeSala materialDeSala = materialDeSalaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material de sala no encontrado con id: " + id));
        materialDeSala.setSalaId(dto.getSalaId());
        materialDeSala.setMaterialId(dto.getMaterialId());
        materialDeSala.setCantidad(dto.getCantidad());
        return MaterialDeSalaMapper.toDTO(materialDeSalaRepository.save(materialDeSala));
    }

    public void delete(Integer id) {
        if (!materialDeSalaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Material de sala no encontrado con id: " + id);
        }
        materialDeSalaRepository.deleteById(id);
    }
}
