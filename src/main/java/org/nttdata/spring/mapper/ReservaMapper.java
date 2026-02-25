package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.ReservaDTO;
import org.nttdata.spring.entity.Puesto;
import org.nttdata.spring.entity.Reserva;
import org.nttdata.spring.entity.Usuario;

public class ReservaMapper {

    private ReservaMapper() {
    }

    public static ReservaDTO toDTO(Reserva entity) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(entity.getId());
        dto.setIdUsuario(entity.getUsuario() != null ? entity.getUsuario().getId().intValue() : null);
        dto.setIdPuesto(entity.getPuesto() != null ? entity.getPuesto().getId() : null); // ← Cambiado
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaFinal(entity.getFechaFinal());
        dto.setAsistio(entity.getAsistio());
        dto.setDeleted(entity.getDeleted());
        return dto;
    }

    public static Reserva toEntity(ReservaDTO dto) {
        Reserva entity = new Reserva();
        entity.setId(dto.getId());
        if (dto.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getIdUsuario().longValue());
            entity.setUsuario(usuario);
        }
        if (dto.getIdPuesto() != null) {
            Puesto puesto = new Puesto();
            puesto.setId(dto.getIdPuesto());
            entity.setPuesto(puesto);
        }
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFinal(dto.getFechaFinal());
        entity.setAsistio(dto.getAsistio());
        entity.setDeleted(dto.getDeleted());
        return entity;
    }
}