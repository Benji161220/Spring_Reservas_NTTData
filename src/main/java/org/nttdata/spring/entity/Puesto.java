package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "puestos")
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_zona", nullable = false)
    private Zona zona;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo", nullable = false, unique = true, length = 50)
    private String codigo; // Ej: "MAD-P1-ZA-001"

    @OneToMany(mappedBy = "puesto", cascade = CascadeType.ALL)
    private List<Reserva> reservas;
}