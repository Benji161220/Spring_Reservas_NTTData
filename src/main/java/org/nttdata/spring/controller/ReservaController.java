package org.nttdata.spring.controller;

import jakarta.validation.Valid;
import org.nttdata.spring.dto.ReservaDTO;
import org.nttdata.spring.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    @Operation(summary = "Listar reservas (no eliminadas)")
    public ResponseEntity<List<ReservaDTO>> findAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reserva por ID")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(reservaService.findById(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar reservas por usuario")
    public ResponseEntity<List<ReservaDTO>> findByUsuarioId(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(reservaService.findByUsuarioId(usuarioId));
    }

    @GetMapping("/puesto/{puestoId}")
    @Operation(summary = "Listar reservas por puesto")
    public ResponseEntity<List<ReservaDTO>> findByPuestoId(@PathVariable Integer puestoId) {
        return ResponseEntity.ok(reservaService.findByPuestoId(puestoId));
    }

    @PostMapping
    @Operation(summary = "Crear reserva")
    public ResponseEntity<ReservaDTO> create(@Valid @RequestBody ReservaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reserva por ID")
    public ResponseEntity<ReservaDTO> update(@PathVariable Integer id, @Valid @RequestBody ReservaDTO dto) {
        return ResponseEntity.ok(reservaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar (soft delete) reserva por ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        reservaService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
