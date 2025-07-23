package com.udemy.matriculas.registros.services;

import com.udemy.matriculas.auth.models.entities.Rol;
import com.udemy.matriculas.auth.models.entities.Usuario;
import com.udemy.matriculas.auth.models.enums.RolList;
import com.udemy.matriculas.auth.repositories.RolRepository;
import com.udemy.matriculas.auth.repositories.UsuarioRepository;
import com.udemy.matriculas.registros.models.dtos.EstudianteDTO;
import com.udemy.matriculas.registros.models.entities.Estudiante;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import com.udemy.matriculas.registros.repositories.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Estudiante registrarEstudiante(EstudianteDTO dto) {
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está en uso.");
        }

        Rol rolStudent = rolRepository.findByNombre(RolList.ROLE_STUDENT)
                .orElseThrow(() -> new RuntimeException("Error: No se encontró el rol de estudiante."));

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(rolStudent);

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setCelular(dto.getCelular());
        estudiante.setDni(dto.getDni());
        estudiante.setEstado(EstadoUsuario.ACTIVO);
        estudiante.setUsuario(usuario);

        return estudianteRepository.save(estudiante);
    }

    @Transactional(readOnly = true)
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Estudiante obtenerPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }

    @Transactional
    public Estudiante actualizarEstudiante(Long id, EstudianteDTO dto) {
        Estudiante estudiante = obtenerPorId(id);

        if (!estudiante.getUsuario().getUsername().equals(dto.getUsername())) {
            if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
                throw new IllegalArgumentException("El nuevo correo ya está en uso.");
            }
            estudiante.getUsuario().setUsername(dto.getUsername());
        }

        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setCelular(dto.getCelular());
        estudiante.setDni(dto.getDni());

        return estudianteRepository.save(estudiante);
    }

    @Transactional
    public void cambiarEstadoEstudiante(Long id) {
        Estudiante estudiante = obtenerPorId(id);
        if (estudiante.getEstado() == EstadoUsuario.ACTIVO) {
            estudiante.setEstado(EstadoUsuario.INACTIVO);
        } else {
            estudiante.setEstado(EstadoUsuario.ACTIVO);
        }
        estudianteRepository.save(estudiante);
    }

    public long contarEstudiantes() {
        return estudianteRepository.count();
    }
}

