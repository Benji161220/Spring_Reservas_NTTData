package org.nttdata.spring.controller;

import jakarta.validation.Valid;
import org.nttdata.spring.dto.ZonaDTO;
import org.nttdata.spring.service.ZonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zonas")
public class ZonaController {

    private final ZonaService zonaService;

    public ZonaController(ZonaService zonaService) {
        this.zonaService = zonaService;
    }

    @GetMapping
    public ResponseEntity<List<ZonaDTO>> findAll() {
        return ResponseEntity.ok(zonaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(zonaService.findById(id));
    }

    @GetMapping("/planta/{plantaId}")
    public ResponseEntity<List<ZonaDTO>> findByPlantaId(@PathVariable Integer plantaId) {
        return ResponseEntity.ok(zonaService.findByPlantaId(plantaId));
    }

    @PostMapping
    public ResponseEntity<ZonaDTO> create(@Valid @RequestBody ZonaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(zonaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZonaDTO> update(@PathVariable Integer id, @Valid @RequestBody ZonaDTO dto) {
        return ResponseEntity.ok(zonaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        zonaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
