package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "plantas")
public class Planta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "oficina_id", nullable = false)
    private Integer oficinaId;

    @Column(name = "numero", nullable = false)
    private Integer numero;
}
