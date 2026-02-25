package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "plantas")
public class Planta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "oficina_id", nullable = false)
    private Oficina oficina;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @OneToMany(mappedBy = "planta", cascade = CascadeType.ALL)
    private List<Zona> zonas;
}
