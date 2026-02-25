package org.nttdata.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    @Column(name = "penalizaciones")
    private Integer penalizacion = 0;

    @ManyToOne
    @JoinColumn(name = "id_oficina", referencedColumnName = "id")
    private Oficina oficina;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;
}
