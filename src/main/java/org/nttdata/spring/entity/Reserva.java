package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_zona", nullable = false)
    private Integer idZona;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_final", nullable = false)
    private LocalDateTime fechaFinal;

    @Column(name = "asistio")
    private Boolean asistio = true;

    @Column(name = "deleted")
    private Boolean deleted = false;
}
