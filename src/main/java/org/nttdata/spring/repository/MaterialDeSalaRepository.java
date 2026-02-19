package org.nttdata.spring.repository;

import org.nttdata.spring.entity.MaterialDeSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialDeSalaRepository extends JpaRepository<MaterialDeSala, Integer> {
    List<MaterialDeSala> findBySalaId(Integer salaId);
}
