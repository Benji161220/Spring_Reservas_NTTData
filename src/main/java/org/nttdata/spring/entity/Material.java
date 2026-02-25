package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "materiales")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "cantidad_total", nullable = false)
    private Integer cantidadTotal;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<MaterialDeSala> materialesDeSala;
}
