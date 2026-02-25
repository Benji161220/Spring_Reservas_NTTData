package org.nttdata.spring.service;

import org.nttdata.spring.dto.ReservaDTO;
import org.nttdata.spring.entity.Puesto;
import org.nttdata.spring.entity.Reserva;
import org.nttdata.spring.entity.Usuario;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.ReservaMapper;
import org.nttdata.spring.repository.PuestoRepository;
import org.nttdata.spring.repository.ReservaRepository;
import org.nttdata.spring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PuestoRepository puestoRepository;

    public ReservaService(ReservaRepository reservaRepository,
                          UsuarioRepository usuarioRepository,
                          PuestoRepository puestoRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.puestoRepository = puestoRepository;
    }

    public ReservaDTO create(ReservaDTO dto) {

        Usuario usuario = usuarioRepository.findById(Long.valueOf(dto.getIdUsuario()))
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Puesto puesto = puestoRepository.findById(dto.getIdPuesto())
                .orElseThrow(() -> new ResourceNotFoundException("Puesto no encontrado"));

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setPuesto(puesto);
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFinal(dto.getFechaFinal());
        reserva.setDeleted(false);

        return ReservaMapper.toDTO(reservaRepository.save(reserva));
    }
}