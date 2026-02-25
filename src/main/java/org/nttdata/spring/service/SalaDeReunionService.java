package org.nttdata.spring.service;

import org.nttdata.spring.dto.SalaDeReunionDTO;
import org.nttdata.spring.entity.Oficina;
import org.nttdata.spring.entity.SalaDeReunion;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.SalaDeReunionMapper;
import org.nttdata.spring.repository.OficinaRepository;
import org.nttdata.spring.repository.SalaDeReunionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaDeReunionService {

    private final SalaDeReunionRepository salaRepository;
    private final OficinaRepository oficinaRepository;

    public SalaDeReunionService(SalaDeReunionRepository salaRepository,
                                OficinaRepository oficinaRepository) {
        this.salaRepository = salaRepository;
        this.oficinaRepository = oficinaRepository;
    }

    public List<SalaDeReunionDTO> findAll() {
        return salaRepository.findAll().stream()
                .map(SalaDeReunionMapper::toDTO)
                .toList();
    }

    public SalaDeReunionDTO findById(Integer id) {
        SalaDeReunion sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala no encontrada con id: " + id));
        return SalaDeReunionMapper.toDTO(sala);
    }

    public SalaDeReunionDTO create(SalaDeReunionDTO dto) {

        Oficina oficina = oficinaRepository.findById(dto.getOficinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Oficina no encontrada"));

        SalaDeReunion sala = new SalaDeReunion();
        sala.setNombre(dto.getNombre());
        sala.setCapacidad(dto.getCapacidad());
        sala.setOficina(oficina);

        return SalaDeReunionMapper.toDTO(salaRepository.save(sala));
    }

    public SalaDeReunionDTO update(Integer id, SalaDeReunionDTO dto) {

        SalaDeReunion sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala no encontrada con id: " + id));

        Oficina oficina = oficinaRepository.findById(dto.getOficinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Oficina no encontrada"));

        sala.setNombre(dto.getNombre());
        sala.setCapacidad(dto.getCapacidad());
        sala.setOficina(oficina);

        return SalaDeReunionMapper.toDTO(salaRepository.save(sala));
    }

    public void delete(Integer id) {
        if (!salaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sala no encontrada con id: " + id);
        }
        salaRepository.deleteById(id);
    }
}