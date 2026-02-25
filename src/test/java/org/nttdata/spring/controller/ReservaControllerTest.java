package org.nttdata.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.nttdata.spring.dto.ReservaDTO;
import org.nttdata.spring.security.JwtUtil;
import org.nttdata.spring.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReservaService reservaService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser // Simula un usuario para que el test no de error 401/403
    void crearReserva_Endpoint_DebeRetornar201() throws Exception {
        ReservaDTO dto = new ReservaDTO();
        dto.setIdUsuario(1);
        dto.setIdPuesto(1);
        dto.setFechaInicio(java.time.LocalDateTime.now());
        dto.setFechaFinal(java.time.LocalDateTime.now().plusHours(1));

        // WHEN
        when(reservaService.create(any(ReservaDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/reservas")
                        .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
    @Test
    @WithMockUser
    void testDeleteReserva_Endpoint_DebeRetornar201() throws Exception {
        mockMvc.perform(delete("/api/reservas/1")
                .with(csrf()))
                .andExpect(status().isNoContent());
    }
}
