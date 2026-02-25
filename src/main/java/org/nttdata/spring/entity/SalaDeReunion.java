package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "salas_de_reuniones")
public class SalaDeReunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "oficina_id", nullable = false)
    private Integer oficinaId;
}
