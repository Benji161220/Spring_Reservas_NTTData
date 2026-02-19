package org.nttdata.spring.repository;

import org.nttdata.spring.entity.SalaDeReunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaDeReunionRepository extends JpaRepository<SalaDeReunion, Integer> {
    List<SalaDeReunion> findByOficinaId(Integer oficinaId);
}
