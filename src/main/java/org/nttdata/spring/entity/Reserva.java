package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reservas")
public class Reserva {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_puesto", nullable = false)
    private Puesto puesto;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_final", nullable = false)
    private LocalDateTime fechaFinal;

    @Column(name = "asistio")
    private Boolean asistio = true;

    @Column(name = "deleted")
    private Boolean deleted = false;
}