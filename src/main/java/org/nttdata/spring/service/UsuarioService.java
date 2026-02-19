package org.nttdata.spring.service;

import org.nttdata.spring.dto.UsuarioDTO;
import org.nttdata.spring.entity.Usuario;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.UsuarioMapper;
import org.nttdata.spring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return UsuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        usuario.setNombre(dto.getNombre());
        usuario.setRol(dto.getRol());
        usuario.setPenalizacion(dto.getPenalizacion());
        usuario.setOficinaActual(dto.getOficinaActual());
        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
