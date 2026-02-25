package org.nttdata.spring.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.nttdata.spring.entity.Puesto;
import org.nttdata.spring.entity.Reserva;
import org.nttdata.spring.entity.Usuario;
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

    @Autowired
    private EntityManager entityManager;

    /*
     * @Contaniner
     * static PostgreSQLContainer<?> postgres = new
     * PostgreSQLContainer<>("postgres:15-alpine")
     * 
     * @DynamicPropertySource
     * static void configureProperties(DynamicPropertyRegistry registry) {
     * registry.add("spring.datasource.url", postgres::getJdbcUrl);
     * registry.add("spring.datasource.username", postgres::getUsername);
     * registry.add("spring.datasource.password", postgres::getPassword);
     * }
     */

    @Test
    void testFindByDeletedFalse() {
        // Arrange (Preparamos datos en la DB real en memoria)
        Usuario usuario = new Usuario();
        usuario.setNombre("Test User");
        usuario.setEmail("test@nttdata.com");
        entityManager.persist(usuario);

        Puesto puesto = new Puesto();
        puesto.setNombre("Puesto 1");
        entityManager.persist(puesto);

        Reserva activa = new Reserva();
        activa.setUsuario(usuario);
        activa.setPuesto(puesto);
        activa.setFechaInicio(LocalDateTime.now());
        activa.setFechaFinal(LocalDateTime.now().plusHours(1));
        activa.setDeleted(false);
        reservaRepository.save(activa);

        List<Reserva> resultado = reservaRepository.findByDeletedFalse();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertFalse(resultado.get(0).getDeleted());
    }

    @Test
    void testFindByIdUsuarioAndDeletedFalse() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setNombre("User 99");
        usuario.setEmail("99@nttdata.com");
        entityManager.persist(usuario);

        Puesto puesto = new Puesto();
        puesto.setNombre("Puesto 2");
        entityManager.persist(puesto);

        Reserva r = new Reserva();
        r.setUsuario(usuario);
        r.setPuesto(puesto);
        r.setFechaInicio(LocalDateTime.now());
        r.setFechaFinal(LocalDateTime.now().plusHours(1));
        r.setDeleted(false);
        reservaRepository.save(r);


        List<Reserva> resultado = reservaRepository.findByIdUsuarioAndDeletedFalse(Math.toIntExact(usuario.getId()));

        // Assert
        assertEquals(1, resultado.size());
        assertEquals(usuario.getId(), resultado.get(0).getUsuario().getId());
    }
}