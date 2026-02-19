package org.nttdata.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ZonaDTO {
    private Integer id;

    @NotNull(message = "El ID de planta es obligatorio")
    private Integer idPlanta;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;
}
