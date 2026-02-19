package org.nttdata.spring.repository;

import org.nttdata.spring.entity.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Integer> {
}
