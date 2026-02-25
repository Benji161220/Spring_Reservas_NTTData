package org.nttdata.spring.service;

import org.nttdata.spring.dto.ZonaDTO;
import org.nttdata.spring.entity.Planta;
import org.nttdata.spring.entity.Zona;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.ZonaMapper;
import org.nttdata.spring.repository.ZonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaService {

    private final ZonaRepository zonaRepository;

    public ZonaService(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
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

    public List<ZonaDTO> findByPlantaId(Integer plantaId) {
        return zonaRepository.findByPlantaId(plantaId).stream()
                .map(ZonaMapper::toDTO)
                .toList();
    }

    public ZonaDTO create(ZonaDTO dto) {
        Zona zona = ZonaMapper.toEntity(dto);
        zona.setId(null);
        return ZonaMapper.toDTO(zonaRepository.save(zona));
    }

    public ZonaDTO update(Integer id, ZonaDTO dto) {
        Zona zona = zonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zona no encontrada con id: " + id));
        if (dto.getIdPlanta() != null) {
            Planta planta = new Planta();
            planta.setId(dto.getIdPlanta());
            zona.setPlanta(planta);
        } else {
            zona.setPlanta(null);
        }
        zona.setNombre(dto.getNombre());
        return ZonaMapper.toDTO(zonaRepository.save(zona));
    }

    public void delete(Integer id) {
        if (!zonaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Zona no encontrada con id: " + id);
        }
        zonaRepository.deleteById(id);
    }
}
