package org.nttdata.spring.service;

import org.nttdata.spring.dto.SalaDeReunionDTO;
import org.nttdata.spring.entity.Oficina;
import org.nttdata.spring.entity.SalaDeReunion;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.SalaDeReunionMapper;
import org.nttdata.spring.repository.SalaDeReunionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaDeReunionService {

    private final SalaDeReunionRepository salaDeReunionRepository;

    public SalaDeReunionService(SalaDeReunionRepository salaDeReunionRepository) {
        this.salaDeReunionRepository = salaDeReunionRepository;
    }

    public List<SalaDeReunionDTO> findAll() {
        return salaDeReunionRepository.findAll().stream()
                .map(SalaDeReunionMapper::toDTO)
                .toList();
    }

    public SalaDeReunionDTO findById(Integer id) {
        SalaDeReunion sala = salaDeReunionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala de reunión no encontrada con id: " + id));
        return SalaDeReunionMapper.toDTO(sala);
    }

    public List<SalaDeReunionDTO> findByOficinaId(Integer oficinaId) {
        return salaDeReunionRepository.findByOficinaId(oficinaId).stream()
                .map(SalaDeReunionMapper::toDTO)
                .toList();
    }

    public SalaDeReunionDTO create(SalaDeReunionDTO dto) {
        SalaDeReunion sala = SalaDeReunionMapper.toEntity(dto);
        sala.setId(null);
        return SalaDeReunionMapper.toDTO(salaDeReunionRepository.save(sala));
    }

    public SalaDeReunionDTO update(Integer id, SalaDeReunionDTO dto) {
        SalaDeReunion sala = salaDeReunionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala de reunión no encontrada con id: " + id));
        sala.setNombre(dto.getNombre());
        sala.setCapacidad(dto.getCapacidad());
        if (dto.getOficinaId() != null) {
            Oficina oficina = new Oficina();
            oficina.setId(dto.getOficinaId());
            sala.setOficina(oficina);
        } else {
            sala.setOficina(null);
        }
        return SalaDeReunionMapper.toDTO(salaDeReunionRepository.save(sala));
    }

    public void delete(Integer id) {
        if (!salaDeReunionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sala de reunión no encontrada con id: " + id);
        }
        salaDeReunionRepository.deleteById(id);
    }
}
