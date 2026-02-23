package org.nttdata.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservaDTO {
    private Integer id;

    @NotNull(message = "El ID de usuario es obligatorio")
    private Integer idUsuario;

    @NotNull(message = "El ID de puesto es obligatorio")
    private Integer idPuesto;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fechaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull(message = "La fecha final es obligatoria")
    private LocalDateTime fechaFinal;

    private Boolean asistio;
    private Boolean deleted;
}