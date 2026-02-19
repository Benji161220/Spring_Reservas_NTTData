package org.nttdata.spring.service;

import org.nttdata.spring.dto.LoginRequest;
import org.nttdata.spring.dto.LoginResponse;
import org.nttdata.spring.dto.RegistroRequest;
import org.nttdata.spring.dto.UsuarioDTO;
import org.nttdata.spring.entity.Usuario;
import org.nttdata.spring.mapper.UsuarioMapper;
import org.nttdata.spring.repository.UsuarioRepository;
import org.nttdata.spring.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UsuarioDTO register(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        usuario.setRol("usuario");
        usuario.setPenalizacion(0);
        usuario.setCreadoEn(LocalDateTime.now());

        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol());
        return new LoginResponse(token, UsuarioMapper.toDTO(usuario));
    }
}
