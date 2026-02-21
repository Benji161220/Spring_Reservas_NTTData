package org.nttdata.spring.controller;

import org.nttdata.spring.dto.PuestoDTO;
import org.nttdata.spring.service.PuestoService;
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

    @GetMapping("/zona/{zonaId}")
    public ResponseEntity<List<PuestoDTO>> findByZonaId(@PathVariable Integer zonaId) {
        return ResponseEntity.ok(puestoService.findByZonaId(zonaId));
    }
}