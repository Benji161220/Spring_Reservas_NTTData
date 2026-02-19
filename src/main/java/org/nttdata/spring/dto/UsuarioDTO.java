package org.nttdata.spring.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
    private Integer penalizacion;
    private Integer oficinaActual;
    private LocalDateTime creadoEn;
}
