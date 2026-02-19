package org.nttdata.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SalaDeReunionDTO {
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotNull(message = "La capacidad es obligatoria")
    private Integer capacidad;

    @NotNull(message = "El ID de oficina es obligatorio")
    private Integer oficinaId;
}
