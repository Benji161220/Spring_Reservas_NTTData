package org.nttdata.spring.service;

import org.nttdata.spring.dto.UsuarioDTO;
import org.nttdata.spring.entity.Oficina;
import org.nttdata.spring.entity.Usuario;
import org.nttdata.spring.exception.ResourceNotFoundException;
import org.nttdata.spring.mapper.UsuarioMapper;
import org.nttdata.spring.repository.OficinaRepository;
import org.nttdata.spring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final OficinaRepository oficinaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          OficinaRepository oficinaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.oficinaRepository = oficinaRepository;
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

        Oficina oficina = oficinaRepository.findById(dto.getOficinaActual())
                .orElseThrow(() -> new ResourceNotFoundException("Oficina no encontrada"));

        usuario.setNombre(dto.getNombre());
        usuario.setRol(dto.getRol());
        usuario.setPenalizacion(dto.getPenalizacion());
        usuario.setOficina(oficina);

        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}