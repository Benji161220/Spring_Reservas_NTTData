package org.nttdata.spring.repository;

import org.nttdata.spring.entity.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Integer> {
    List<Planta> findByOficinaId(Integer oficinaId);
}
