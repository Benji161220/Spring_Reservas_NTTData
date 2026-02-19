package org.nttdata.spring.controller;

import jakarta.validation.Valid;
import org.nttdata.spring.dto.SalaDeReunionDTO;
import org.nttdata.spring.service.SalaDeReunionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaDeReunionController {

    private final SalaDeReunionService salaDeReunionService;

    public SalaDeReunionController(SalaDeReunionService salaDeReunionService) {
        this.salaDeReunionService = salaDeReunionService;
    }

    @GetMapping
    public ResponseEntity<List<SalaDeReunionDTO>> findAll() {
        return ResponseEntity.ok(salaDeReunionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDeReunionDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(salaDeReunionService.findById(id));
    }

    @GetMapping("/oficina/{oficinaId}")
    public ResponseEntity<List<SalaDeReunionDTO>> findByOficinaId(@PathVariable Integer oficinaId) {
        return ResponseEntity.ok(salaDeReunionService.findByOficinaId(oficinaId));
    }

    @PostMapping
    public ResponseEntity<SalaDeReunionDTO> create(@Valid @RequestBody SalaDeReunionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salaDeReunionService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaDeReunionDTO> update(@PathVariable Integer id,
                                                   @Valid @RequestBody SalaDeReunionDTO dto) {
        return ResponseEntity.ok(salaDeReunionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        salaDeReunionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
