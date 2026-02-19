package org.nttdata.spring.service;

import org.nttdata.spring.dto.MaterialDTO;
import org.nttdata.spring.entity.Material;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.MaterialMapper;
import org.nttdata.spring.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<MaterialDTO> findAll() {
        return materialRepository.findAll().stream()
                .map(MaterialMapper::toDTO)
                .toList();
    }

    public MaterialDTO findById(Integer id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado con id: " + id));
        return MaterialMapper.toDTO(material);
    }

    public MaterialDTO create(MaterialDTO dto) {
        Material material = MaterialMapper.toEntity(dto);
        material.setId(null);
        return MaterialMapper.toDTO(materialRepository.save(material));
    }

    public MaterialDTO update(Integer id, MaterialDTO dto) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado con id: " + id));
        material.setNombre(dto.getNombre());
        material.setCantidadTotal(dto.getCantidadTotal());
        return MaterialMapper.toDTO(materialRepository.save(material));
    }

    public void delete(Integer id) {
        if (!materialRepository.existsById(id)) {
            throw new ResourceNotFoundException("Material no encontrado con id: " + id);
        }
        materialRepository.deleteById(id);
    }
}
