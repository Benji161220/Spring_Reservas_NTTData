package org.nttdata.spring.controller;

import jakarta.validation.Valid;
import org.nttdata.spring.dto.PuestoDTO;
import org.nttdata.spring.service.PuestoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puestos")
public class PuestoController {

    private final PuestoService puestoService;

    public PuestoController(PuestoService puestoService) {
        this.puestoService = puestoService;
    }

    @GetMapping
    public ResponseEntity<List<PuestoDTO>> findAll() {
        return ResponseEntity.ok(puestoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuestoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(puestoService.findById(id));
    }

    @GetMapping("/zona/{zonaId}")
    public ResponseEntity<List<PuestoDTO>> findByZonaId(@PathVariable Integer zonaId) {
        return ResponseEntity.ok(puestoService.findByZonaId(zonaId));
    }

    @PostMapping
    public ResponseEntity<PuestoDTO> create(@Valid @RequestBody PuestoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(puestoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuestoDTO> update(@PathVariable Integer id, @Valid @RequestBody PuestoDTO dto) {
        return ResponseEntity.ok(puestoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        puestoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}