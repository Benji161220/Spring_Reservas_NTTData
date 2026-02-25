package org.nttdata.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nttdata.spring.dto.ReservaDTO;
import org.nttdata.spring.entity.Reserva;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.repository.ReservaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    private Reserva reserva;
    private ReservaDTO reservaDTO;

    @BeforeEach
    void setUp() {
        reserva = new Reserva();
        reserva.setId(1);
        reserva.setIdUsuario(1);
        reserva.setIdPuesto(1);
        reserva.setFechaInicio(LocalDateTime.of(2024, 1, 1, 9, 0));
        reserva.setFechaFinal(LocalDateTime.of(2024, 1, 1, 10, 0));
        reserva.setAsistio(true);
        reserva.setDeleted(false);

        reservaDTO = new ReservaDTO();
        reservaDTO.setIdUsuario(1);
        reservaDTO.setIdPuesto(1);
        reservaDTO.setFechaInicio(reserva.getFechaInicio());
        reservaDTO.setFechaFinal(reserva.getFechaFinal());
    }

    @Test
    void findById_notFound() {
        when(reservaRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reservaService.findById(99));
    }

    // Test Unitario Crear una reserva exitosa
    @Test
    void testCrearReserva_Exito() {
        // WHEN
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        ReservaDTO resultado = reservaService.create(reservaDTO);

        // THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    // Test unitario de lectura
    @Test
    void testFindById_Exito() {

        // WHEN
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));

        ReservaDTO resultado = reservaService.findById(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdUsuario());
    }

    // Test unitario
    @Test
    void softDelete_exito() {
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));

        reservaService.softDelete(1);

        assertTrue(reserva.getDeleted());
        verify(reservaRepository).save(reserva);
    }

    // Test unitario borrado falla
    @Test
    void softDelete_notFound() {
        when(reservaRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reservaService.softDelete(99));
    }
}
