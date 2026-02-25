package org.nttdata.spring.repository;

import org.junit.jupiter.api.Test;
import org.nttdata.spring.entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// Imports test contaniners
// import org.testcontainers.containers.PostgreSQLContainer;
// import org.testcontainers.junit.jupiter.Container;
// import org.testcontainers.junit.jupiter.Testcontainers;
@DataJpaTest
@Testcontainers
// replace = Replace.Any hace que use H2 automáticamente
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ReservaRepositoryTest {

    @Autowired
    private ReservaRepository reservaRepository;

    /*
    @Contaniner
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
     */

    @Test
    void testFindByDeletedFalse() {
        // Arrange (Preparamos datos en la DB real en memoria)
        Reserva activa = new Reserva();
        activa.setIdUsuario(1);
        activa.setIdPuesto(1);
        activa.setFechaInicio(LocalDateTime.now());
        activa.setFechaFinal(LocalDateTime.now().plusHours(1));
        activa.setDeleted(false);
        reservaRepository.save(activa);

        // Act (Llamamos al método del repositorio)
        List<Reserva> resultado = reservaRepository.findByDeletedFalse();

        // Assert (Comprobamos que JPA hizo bien la consulta)
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertFalse(resultado.get(0).getDeleted());
    }

    @Test
    void testFindByIdUsuarioAndDeletedFalse() {
        Reserva r = new Reserva();
        r.setIdUsuario(99);
        r.setIdPuesto(1);
        r.setFechaInicio(LocalDateTime.now());
        r.setFechaFinal(LocalDateTime.now().plusHours(1));
        r.setDeleted(false);
        reservaRepository.save(r);

        List<Reserva> resultado = reservaRepository.findByIdUsuarioAndDeletedFalse(99);

        assertEquals(1, resultado.size());
        assertEquals(99, resultado.get(0).getIdUsuario());
    }
}