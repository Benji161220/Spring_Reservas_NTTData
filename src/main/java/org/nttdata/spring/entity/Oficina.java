package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "oficinas")
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "oficina", cascade = CascadeType.ALL)
    private List<Planta> plantas;

    @OneToMany(mappedBy = "oficina")
    private List<SalaDeReunion> salas;

    @OneToMany(mappedBy = "oficina")
    private List<Usuario> usuarios;
}
