package org.nttdata.spring.repository;

import org.nttdata.spring.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByIdUsuarioAndDeletedFalse(Integer idUsuario);
    List<Reserva> findByIdPuestoAndDeletedFalse(Integer idPuesto);
    List<Reserva> findByDeletedFalse();
}