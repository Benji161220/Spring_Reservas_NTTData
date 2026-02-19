package org.nttdata.spring.controller;

import jakarta.validation.Valid;
import org.nttdata.spring.dto.PlantaDTO;
import org.nttdata.spring.service.PlantaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plantas")
public class PlantaController {

    private final PlantaService plantaService;

    public PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @GetMapping
    public ResponseEntity<List<PlantaDTO>> findAll() {
        return ResponseEntity.ok(plantaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(plantaService.findById(id));
    }

    @GetMapping("/oficina/{oficinaId}")
    public ResponseEntity<List<PlantaDTO>> findByOficinaId(@PathVariable Integer oficinaId) {
        return ResponseEntity.ok(plantaService.findByOficinaId(oficinaId));
    }

    @PostMapping
    public ResponseEntity<PlantaDTO> create(@Valid @RequestBody PlantaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(plantaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantaDTO> update(@PathVariable Integer id, @Valid @RequestBody PlantaDTO dto) {
        return ResponseEntity.ok(plantaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        plantaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
