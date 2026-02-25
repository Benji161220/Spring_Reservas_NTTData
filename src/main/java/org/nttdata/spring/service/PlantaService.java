package org.nttdata.spring.service;

import org.nttdata.spring.dto.PlantaDTO;
import org.nttdata.spring.entity.Oficina;
import org.nttdata.spring.entity.Planta;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.PlantaMapper;
import org.nttdata.spring.repository.PlantaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantaService {

    private final PlantaRepository plantaRepository;

    public PlantaService(PlantaRepository plantaRepository) {
        this.plantaRepository = plantaRepository;
    }

    public List<PlantaDTO> findAll() {
        return plantaRepository.findAll().stream()
                .map(PlantaMapper::toDTO)
                .toList();
    }

    public PlantaDTO findById(Integer id) {
        Planta planta = plantaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planta no encontrada con id: " + id));
        return PlantaMapper.toDTO(planta);
    }

    public List<PlantaDTO> findByOficinaId(Integer oficinaId) {
        return plantaRepository.findByOficinaId(oficinaId).stream()
                .map(PlantaMapper::toDTO)
                .toList();
    }

    public PlantaDTO create(PlantaDTO dto) {
        Planta planta = PlantaMapper.toEntity(dto);
        planta.setId(null);
        return PlantaMapper.toDTO(plantaRepository.save(planta));
    }

    public PlantaDTO update(Integer id, PlantaDTO dto) {
        Planta planta = plantaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planta no encontrada con id: " + id));
        if (dto.getOficinaId() != null) {
            Oficina oficina = new Oficina();
            oficina.setId(dto.getOficinaId());
            planta.setOficina(oficina);
        } else {
            planta.setOficina(null);
        }
        planta.setNumero(dto.getNumero());
        return PlantaMapper.toDTO(plantaRepository.save(planta));
    }

    public void delete(Integer id) {
        if (!plantaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Planta no encontrada con id: " + id);
        }
        plantaRepository.deleteById(id);
    }
}
