package org.nttdata.spring.controller;

import jakarta.validation.Valid;
import org.nttdata.spring.dto.MaterialDTO;
import org.nttdata.spring.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> findAll() {
        return ResponseEntity.ok(materialService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(materialService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MaterialDTO> create(@Valid @RequestBody MaterialDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> update(@PathVariable Integer id, @Valid @RequestBody MaterialDTO dto) {
        return ResponseEntity.ok(materialService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        materialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
