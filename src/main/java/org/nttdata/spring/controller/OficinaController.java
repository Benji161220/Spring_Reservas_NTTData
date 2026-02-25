package org.nttdata.spring.controller;

import jakarta.validation.Valid;
import org.nttdata.spring.dto.OficinaDTO;
import org.nttdata.spring.service.OficinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/oficinas")
public class OficinaController {

    private final OficinaService oficinaService;

    public OficinaController(OficinaService oficinaService) {
        this.oficinaService = oficinaService;
    }

    @GetMapping
    @Operation(summary = "Listar oficinas")
    public ResponseEntity<List<OficinaDTO>> findAll() {
        return ResponseEntity.ok(oficinaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener oficina por ID")
    public ResponseEntity<OficinaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(oficinaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear oficina")
    public ResponseEntity<OficinaDTO> create(@Valid @RequestBody OficinaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(oficinaService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar oficina por ID")
    public ResponseEntity<OficinaDTO> update(@PathVariable Integer id, @Valid @RequestBody OficinaDTO dto) {
        return ResponseEntity.ok(oficinaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar oficina por ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        oficinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
