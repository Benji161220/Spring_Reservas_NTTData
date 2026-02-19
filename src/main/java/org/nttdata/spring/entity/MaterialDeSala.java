package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "materiales_de_sala")
public class MaterialDeSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sala_id", nullable = false)
    private Integer salaId;

    @Column(name = "material_id", nullable = false)
    private Integer materialId;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}
