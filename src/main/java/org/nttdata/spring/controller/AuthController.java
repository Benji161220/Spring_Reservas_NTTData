package org.nttdata.spring.controller;

import jakarta.validation.Valid;
import org.nttdata.spring.dto.LoginRequest;
import org.nttdata.spring.dto.LoginResponse;
import org.nttdata.spring.dto.RegistroRequest;
import org.nttdata.spring.dto.UsuarioDTO;
import org.nttdata.spring.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    @Operation(summary = "Registrar un nuevo usuario")
    public ResponseEntity<UsuarioDTO> register(@Valid @RequestBody RegistroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión y obtener JWT")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
