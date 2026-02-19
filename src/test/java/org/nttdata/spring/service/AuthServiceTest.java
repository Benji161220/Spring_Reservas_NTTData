package org.nttdata.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nttdata.spring.dto.LoginRequest;
import org.nttdata.spring.dto.LoginResponse;
import org.nttdata.spring.dto.RegistroRequest;
import org.nttdata.spring.dto.UsuarioDTO;
import org.nttdata.spring.entity.Usuario;
import org.nttdata.spring.repository.UsuarioRepository;
import org.nttdata.spring.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Test User");
        usuario.setEmail("test@test.com");
        usuario.setPasswordHash("encodedPassword");
        usuario.setRol("usuario");
        usuario.setPenalizacion(0);
        usuario.setCreadoEn(LocalDateTime.now());
    }

    @Test
    void register_success() {
        RegistroRequest request = new RegistroRequest();
        request.setNombre("Test User");
        request.setEmail("test@test.com");
        request.setPassword("password123");

        when(usuarioRepository.existsByEmail("test@test.com")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO result = authService.register(request);

        assertNotNull(result);
        assertEquals("Test User", result.getNombre());
        assertEquals("test@test.com", result.getEmail());
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void register_emailAlreadyExists() {
        RegistroRequest request = new RegistroRequest();
        request.setEmail("test@test.com");

        when(usuarioRepository.existsByEmail("test@test.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> authService.register(request));
    }

    @Test
    void login_success() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("password123");

        when(usuarioRepository.findByEmail("test@test.com")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);
        when(jwtUtil.generateToken("test@test.com", "usuario")).thenReturn("jwt-token");

        LoginResponse result = authService.login(request);

        assertNotNull(result);
        assertEquals("jwt-token", result.getToken());
        assertEquals("test@test.com", result.getUsuario().getEmail());
    }

    @Test
    void login_invalidCredentials() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("wrong");

        when(usuarioRepository.findByEmail("test@test.com")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("wrong", "encodedPassword")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> authService.login(request));
    }

    @Test
    void login_userNotFound() {
        LoginRequest request = new LoginRequest();
        request.setEmail("notfound@test.com");
        request.setPassword("password");

        when(usuarioRepository.findByEmail("notfound@test.com")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> authService.login(request));
    }
}
