package org.nttdata.spring.service;

import org.nttdata.spring.dto.ReservaDTO;
import org.nttdata.spring.entity.Reserva;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.ReservaMapper;
import org.nttdata.spring.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<ReservaDTO> findAll() {
        return reservaRepository.findByDeletedFalse().stream()
                .map(ReservaMapper::toDTO)
                .toList();
    }

    public ReservaDTO findById(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));
        return ReservaMapper.toDTO(reserva);
    }

    public List<ReservaDTO> findByUsuarioId(Integer usuarioId) {
        return reservaRepository.findByIdUsuarioAndDeletedFalse(usuarioId).stream()
                .map(ReservaMapper::toDTO)
                .toList();
    }

    public List<ReservaDTO> findByZonaId(Integer zonaId) {
        return reservaRepository.findByIdZonaAndDeletedFalse(zonaId).stream()
                .map(ReservaMapper::toDTO)
                .toList();
    }

    public ReservaDTO create(ReservaDTO dto) {
        Reserva reserva = ReservaMapper.toEntity(dto);
        reserva.setId(null);
        reserva.setAsistio(null);
        reserva.setDeleted(false);
        return ReservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public ReservaDTO update(Integer id, ReservaDTO dto) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));
        reserva.setIdUsuario(dto.getIdUsuario());
        reserva.setIdZona(dto.getIdZona());
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFinal(dto.getFechaFinal());
        reserva.setAsistio(dto.getAsistio());
        return ReservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public void softDelete(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));
        reserva.setDeleted(true);
        reservaRepository.save(reserva);
    }
}
