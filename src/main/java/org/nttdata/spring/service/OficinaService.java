package org.nttdata.spring.service;

import org.nttdata.spring.dto.OficinaDTO;
import org.nttdata.spring.entity.Oficina;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.OficinaMapper;
import org.nttdata.spring.repository.OficinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OficinaService {

    private final OficinaRepository oficinaRepository;

    public OficinaService(OficinaRepository oficinaRepository) {
        this.oficinaRepository = oficinaRepository;
    }

    public List<OficinaDTO> findAll() {
        return oficinaRepository.findAll().stream()
                .map(OficinaMapper::toDTO)
                .toList();
    }

    public OficinaDTO findById(Integer id) {
        Oficina oficina = oficinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oficina no encontrada con id: " + id));
        return OficinaMapper.toDTO(oficina);
    }

    public OficinaDTO create(OficinaDTO dto) {
        Oficina oficina = OficinaMapper.toEntity(dto);
        oficina.setId(null);
        return OficinaMapper.toDTO(oficinaRepository.save(oficina));
    }

    public OficinaDTO update(Integer id, OficinaDTO dto) {
        Oficina oficina = oficinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oficina no encontrada con id: " + id));
        oficina.setNombre(dto.getNombre());
        return OficinaMapper.toDTO(oficinaRepository.save(oficina));
    }

    public void delete(Integer id) {
        if (!oficinaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Oficina no encontrada con id: " + id);
        }
        oficinaRepository.deleteById(id);
    }
}
