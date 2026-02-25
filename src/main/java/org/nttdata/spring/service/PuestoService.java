package org.nttdata.spring.service;

import org.nttdata.spring.dto.PuestoDTO;
import org.nttdata.spring.entity.Puesto;
import org.nttdata.spring.entity.Zona;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.PuestoMapper;
import org.nttdata.spring.repository.PuestoRepository;
import org.nttdata.spring.repository.ZonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoService {

    private final PuestoRepository puestoRepository;
    private final ZonaRepository zonaRepository;

    public PuestoService(PuestoRepository puestoRepository,
                         ZonaRepository zonaRepository) {
        this.puestoRepository = puestoRepository;
        this.zonaRepository = zonaRepository;
    }

    public PuestoDTO create(PuestoDTO dto) {

        if (puestoRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un puesto con ese código");
        }

        Zona zona = zonaRepository.findById(dto.getIdZona())
                .orElseThrow(() -> new ResourceNotFoundException("Zona no encontrada"));

        Puesto puesto = new Puesto();
        puesto.setNombre(dto.getNombre());
        puesto.setCodigo(dto.getCodigo());
        puesto.setZona(zona);

        return PuestoMapper.toDTO(puestoRepository.save(puesto));
    }

    public PuestoDTO update(Integer id, PuestoDTO dto) {

        Puesto puesto = puestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Puesto no encontrado"));

        Zona zona = zonaRepository.findById(dto.getIdZona())
                .orElseThrow(() -> new ResourceNotFoundException("Zona no encontrada"));

        puesto.setNombre(dto.getNombre());
        puesto.setCodigo(dto.getCodigo());
        puesto.setZona(zona);

        return PuestoMapper.toDTO(puestoRepository.save(puesto));
    }
}