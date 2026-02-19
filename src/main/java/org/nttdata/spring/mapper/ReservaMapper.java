package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.ReservaDTO;
import org.nttdata.spring.entity.Reserva;

public class ReservaMapper {

    private ReservaMapper() {}

    public static ReservaDTO toDTO(Reserva entity) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(entity.getId());
        dto.setIdUsuario(entity.getIdUsuario());
        dto.setIdZona(entity.getIdZona());
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaFinal(entity.getFechaFinal());
        dto.setAsistio(entity.getAsistio());
        dto.setDeleted(entity.getDeleted());
        return dto;
    }

    public static Reserva toEntity(ReservaDTO dto) {
        Reserva entity = new Reserva();
        entity.setId(dto.getId());
        entity.setIdUsuario(dto.getIdUsuario());
        entity.setIdZona(dto.getIdZona());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFinal(dto.getFechaFinal());
        entity.setAsistio(dto.getAsistio());
        entity.setDeleted(dto.getDeleted());
        return entity;
    }
}
