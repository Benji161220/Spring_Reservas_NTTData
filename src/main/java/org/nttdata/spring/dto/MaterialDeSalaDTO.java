package org.nttdata.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MaterialDeSalaDTO {
    private Integer id;

    @NotNull(message = "El ID de sala es obligatorio")
    private Integer salaId;

    @NotNull(message = "El ID de material es obligatorio")
    private Integer materialId;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;
}
