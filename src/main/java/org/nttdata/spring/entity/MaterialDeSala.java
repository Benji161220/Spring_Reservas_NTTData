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

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private SalaDeReunion sala;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;


    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}
