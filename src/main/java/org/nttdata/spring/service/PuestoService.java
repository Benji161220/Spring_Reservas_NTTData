package org.nttdata.spring.service;

import org.nttdata.spring.dto.PuestoDTO;
import org.nttdata.spring.entity.Puesto;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.PuestoMapper;
import org.nttdata.spring.repository.PuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoService {

    private final PuestoRepository puestoRepository;

    public PuestoService(PuestoRepository puestoRepository) {
        this.puestoRepository = puestoRepository;
    }

    public List<PuestoDTO> findAll() {
        return puestoRepository.findAll().stream()
                .map(PuestoMapper::toDTO)
                .toList();
    }

    public PuestoDTO findById(Integer id) {
        Puesto puesto = puestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Puesto no encontrado con id: " + id));
        return PuestoMapper.toDTO(puesto);
    }

    public List<PuestoDTO> findByZonaId(Integer zonaId) {
        return puestoRepository.findByIdZona(zonaId).stream()
                .map(PuestoMapper::toDTO)
                .toList();
    }

    public PuestoDTO create(PuestoDTO dto) {
        if (puestoRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un puesto con el código: " + dto.getCodigo());
        }
        Puesto puesto = PuestoMapper.toEntity(dto);
        puesto.setId(null);
        return PuestoMapper.toDTO(puestoRepository.save(puesto));
    }

    public PuestoDTO update(Integer id, PuestoDTO dto) {
        Puesto puesto = puestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Puesto no encontrado con id: " + id));
        puesto.setIdZona(dto.getIdZona());
        puesto.setNombre(dto.getNombre());
        puesto.setCodigo(dto.getCodigo());
        return PuestoMapper.toDTO(puestoRepository.save(puesto));
    }

    public void delete(Integer id) {
        if (!puestoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Puesto no encontrado con id: " + id);
        }
        puestoRepository.deleteById(id);
    }
}