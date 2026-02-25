package org.nttdata.spring.service;

import org.nttdata.spring.dto.ZonaDTO;
import org.nttdata.spring.entity.Planta;
import org.nttdata.spring.entity.Zona;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.ZonaMapper;
import org.nttdata.spring.repository.PlantaRepository;
import org.nttdata.spring.repository.ZonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaService {

    private final ZonaRepository zonaRepository;
    private final PlantaRepository plantaRepository;

    public ZonaService(ZonaRepository zonaRepository,
                       PlantaRepository plantaRepository) {
        this.zonaRepository = zonaRepository;
        this.plantaRepository = plantaRepository;
    }

    public List<ZonaDTO> findAll() {
        return zonaRepository.findAll().stream()
                .map(ZonaMapper::toDTO)
                .toList();
    }

    public ZonaDTO findById(Integer id) {
        Zona zona = zonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zona no encontrada con id: " + id));
        return ZonaMapper.toDTO(zona);
    }

    public ZonaDTO create(ZonaDTO dto) {

        Planta planta = plantaRepository.findById(dto.getIdPlanta())
                .orElseThrow(() -> new ResourceNotFoundException("Planta no encontrada"));

        Zona zona = new Zona();
        zona.setNombre(dto.getNombre());
        zona.setPlanta(planta);

        return ZonaMapper.toDTO(zonaRepository.save(zona));
    }

    public ZonaDTO update(Integer id, ZonaDTO dto) {

        Zona zona = zonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zona no encontrada con id: " + id));

        Planta planta = plantaRepository.findById(dto.getIdPlanta())
                .orElseThrow(() -> new ResourceNotFoundException("Planta no encontrada"));

        zona.setNombre(dto.getNombre());
        zona.setPlanta(planta);

        return ZonaMapper.toDTO(zonaRepository.save(zona));
    }

    public void delete(Integer id) {
        if (!zonaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Zona no encontrada con id: " + id);
        }
        zonaRepository.deleteById(id);
    }
}