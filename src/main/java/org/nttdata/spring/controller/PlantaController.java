package org.nttdata.spring.controller;

import jakarta.validation.Valid;
import org.nttdata.spring.dto.PlantaDTO;
import org.nttdata.spring.service.PlantaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/plantas")
public class PlantaController {

    private final PlantaService plantaService;

    public PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @GetMapping
    @Operation(summary = "Listar plantas")
    public ResponseEntity<List<PlantaDTO>> findAll() {
        return ResponseEntity.ok(plantaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener planta por ID")
    public ResponseEntity<PlantaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(plantaService.findById(id));
    }

    @GetMapping("/oficina/{oficinaId}")
    @Operation(summary = "Listar plantas por oficina")
    public ResponseEntity<List<PlantaDTO>> findByOficinaId(@PathVariable Integer oficinaId) {
        return ResponseEntity.ok(plantaService.findByOficinaId(oficinaId));
    }

    @PostMapping
    @Operation(summary = "Crear planta")
    public ResponseEntity<PlantaDTO> create(@Valid @RequestBody PlantaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(plantaService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar planta por ID")
    public ResponseEntity<PlantaDTO> update(@PathVariable Integer id, @Valid @RequestBody PlantaDTO dto) {
        return ResponseEntity.ok(plantaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar planta por ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        plantaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
