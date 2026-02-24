package org.nttdata.spring.mapper;

import org.nttdata.spring.dto.UsuarioDTO;
import org.nttdata.spring.entity.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {}

    public static UsuarioDTO toDTO(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setEmail(entity.getEmail());
        dto.setRol(entity.getRol());
        dto.setPenalizacion(entity.getPenalizacion());
        dto.setOficinaActual(entity.getIdOficina());
        dto.setCreadoEn(entity.getCreadoEn());
        return dto;
    }
}
