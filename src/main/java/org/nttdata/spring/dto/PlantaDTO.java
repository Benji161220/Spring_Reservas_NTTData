package org.nttdata.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlantaDTO {
    private Integer id;

    @NotNull(message = "El ID de oficina es obligatorio")
    private Integer oficinaId;

    @NotNull(message = "El número de planta es obligatorio")
    private Integer numero;
}
