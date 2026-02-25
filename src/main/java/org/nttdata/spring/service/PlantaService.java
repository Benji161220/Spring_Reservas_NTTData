package org.nttdata.spring.service;

import org.nttdata.spring.dto.PlantaDTO;
import org.nttdata.spring.entity.Oficina;
import org.nttdata.spring.entity.Planta;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.PlantaMapper;
import org.nttdata.spring.repository.OficinaRepository;
import org.nttdata.spring.repository.PlantaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantaService {

    private final PlantaRepository plantaRepository;
    private final OficinaRepository oficinaRepository;

    public PlantaService(PlantaRepository plantaRepository,
                         OficinaRepository oficinaRepository) {
        this.plantaRepository = plantaRepository;
        this.oficinaRepository = oficinaRepository;
    }

    public PlantaDTO create(PlantaDTO dto) {

        Oficina oficina = oficinaRepository.findById(dto.getOficinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Oficina no encontrada"));

        Planta planta = new Planta();
        planta.setNumero(dto.getNumero());
        planta.setOficina(oficina);

        return PlantaMapper.toDTO(plantaRepository.save(planta));
    }

    public PlantaDTO update(Integer id, PlantaDTO dto) {

        Planta planta = plantaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planta no encontrada"));

        Oficina oficina = oficinaRepository.findById(dto.getOficinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Oficina no encontrada"));

        planta.setNumero(dto.getNumero());
        planta.setOficina(oficina);

        return PlantaMapper.toDTO(plantaRepository.save(planta));
    }
}