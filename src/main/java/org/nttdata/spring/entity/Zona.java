package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "zonas")
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_planta", nullable = false)
    private Integer idPlanta;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
}
