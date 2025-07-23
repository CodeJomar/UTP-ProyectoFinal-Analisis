package com.udemy.matriculas.auth.services;

import com.udemy.matriculas.auth.models.dtos.RegisUsuarioDTO;
import com.udemy.matriculas.auth.models.entities.Rol;
import com.udemy.matriculas.auth.models.entities.Usuario;
import com.udemy.matriculas.auth.models.enums.RolList;
import com.udemy.matriculas.auth.repositories.RolRepository;
import com.udemy.matriculas.auth.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    
    public void registrarUsuario(RegisUsuarioDTO dto) {
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        
        Rol rolDefault = rolRepository.findAll().stream()
            .filter(r -> r.getNombre().equals(RolList.ROLE_ADMIN))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No se encontró el rol por defecto"));
        
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(rolDefault);
        
        usuarioRepository.save(usuario);
    }
    
    public List<Usuario> listadoUsuarios() {
        return usuarioRepository.findAll();
    }
    
    @Transactional // Asegura que las relaciones LAZY puedan ser cargadas
    public Optional<Usuario> buscarPorIdConDetalles(String id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        usuarioOptional.ifPresent(usuario -> {
            // Forzar la carga de las relaciones LAZY si existen
            if (usuario.getDocente() != null) {
                usuario.getDocente().getNombre(); // Acceder a una propiedad para forzar la carga
            }
            if (usuario.getEstudiante() != null) {
                usuario.getEstudiante().getNombre(); // Acceder a una propiedad para forzar la carga
            }
        });
        return usuarioOptional;
    }
    
    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }
    
    public void eliminarUsuario(String id) {
        usuarioRepository.deleteById(id);
    }
    
    public long contarUsuarios() {
        return usuarioRepository.count();
    }
    
}
