package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "zonas")
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_planta", nullable = false)
    private Planta planta;

    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL)
    private List<Puesto> pustos;


    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
}
