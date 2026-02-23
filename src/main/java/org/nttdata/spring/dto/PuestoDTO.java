package org.nttdata.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PuestoDTO {
    private Integer id;

    @NotNull(message = "El ID de zona es obligatorio")
    private Integer idZona;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "El código es obligatorio")
    @Size(max = 50)
    private String codigo;
}