package org.nttdata.spring.controller;

import jakarta.validation.Valid;
import org.nttdata.spring.dto.MaterialDeSalaDTO;
import org.nttdata.spring.service.MaterialDeSalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiales-sala")
public class MaterialDeSalaController {

    private final MaterialDeSalaService materialDeSalaService;

    public MaterialDeSalaController(MaterialDeSalaService materialDeSalaService) {
        this.materialDeSalaService = materialDeSalaService;
    }

    @GetMapping
    public ResponseEntity<List<MaterialDeSalaDTO>> findAll() {
        return ResponseEntity.ok(materialDeSalaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDeSalaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(materialDeSalaService.findById(id));
    }

    @GetMapping("/sala/{salaId}")
    public ResponseEntity<List<MaterialDeSalaDTO>> findBySalaId(@PathVariable Integer salaId) {
        return ResponseEntity.ok(materialDeSalaService.findBySalaId(salaId));
    }

    @PostMapping
    public ResponseEntity<MaterialDeSalaDTO> create(@Valid @RequestBody MaterialDeSalaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materialDeSalaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDeSalaDTO> update(@PathVariable Integer id,
                                                    @Valid @RequestBody MaterialDeSalaDTO dto) {
        return ResponseEntity.ok(materialDeSalaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        materialDeSalaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
