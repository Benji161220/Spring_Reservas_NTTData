package org.nttdata.spring.user.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "usuario") // usa el nombre exacto de la tabla
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "password_h", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    @Column(name = "penalizacion")
    private Integer penalizacion = 0;

    @Column(name = "oficina_actu")
    private Integer oficinaActual;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;
}
