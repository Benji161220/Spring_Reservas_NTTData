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
    }

    @Test
    void findAll_returnsNonDeleted() {
        when(reservaRepository.findByDeletedFalse()).thenReturn(List.of(reserva));

        List<ReservaDTO> result = reservaService.findAll();

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void findById_success() {
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));

        ReservaDTO result = reservaService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getIdUsuario());
    }

    @Test
    void findById_notFound() {
        when(reservaRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reservaService.findById(99));
    }

    @Test
    void create_success() {
        ReservaDTO dto = new ReservaDTO();
        dto.setIdUsuario(1);
        dto.setIdPuesto(1);
        dto.setFechaInicio(LocalDateTime.of(2024, 1, 1, 9, 0));
        dto.setFechaFinal(LocalDateTime.of(2024, 1, 1, 10, 0));

        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        ReservaDTO result = reservaService.create(dto);

        assertNotNull(result);
        verify(reservaRepository).save(any(Reserva.class));
    }

    @Test
    void softDelete_success() {
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        reservaService.softDelete(1);

        assertTrue(reserva.getDeleted());
        verify(reservaRepository).save(reserva);
    }

    @Test
    void softDelete_notFound() {
        when(reservaRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reservaService.softDelete(99));
    }
}
