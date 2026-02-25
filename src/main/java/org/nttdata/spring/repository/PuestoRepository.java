package org.nttdata.spring.repository;

import org.nttdata.spring.entity.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto, Integer> {
    List<Puesto> findByZonaId(Integer zonaId);

    Optional<Puesto> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);
}