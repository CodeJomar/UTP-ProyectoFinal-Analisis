package com.udemy.matriculas.registros.services;

import com.udemy.matriculas.auth.models.entities.Rol;
import com.udemy.matriculas.auth.models.entities.Usuario;
import com.udemy.matriculas.auth.models.enums.RolList;
import com.udemy.matriculas.registros.models.dtos.EstudianteDTO;
import com.udemy.matriculas.registros.models.entities.Estudiante;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import com.udemy.matriculas.registros.repositories.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    
    private final EstudianteRepository estudianteRepository;
    
    // Registrar nuevo estudiante con rol fijo: ROLE_STUDENT
    public Estudiante registrarEstudiante(EstudianteDTO dto) {
        Rol rolStudent = new Rol();
        rolStudent.setNombre(RolList.ROLE_STUDENT);
        
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
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
    
    // Listar todos los estudiantes
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }
    
    // Listar solo estudiantes activos
    public List<Estudiante> listarEstudiantesActivos() {
        return estudianteRepository.findByEstadoUsuario(EstadoUsuario.ACTIVO);
    }
    
    // Obtener estudiante por ID
    public Estudiante obtenerPorId(Long id) {
        return estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }
    
    // Actualizar estudiante
    public Estudiante actualizarEstudiante(Long id, EstudianteDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setCelular(dto.getCelular());
        estudiante.setDni(dto.getDni());
        
        return estudianteRepository.save(estudiante);
    }
    
    // Cambiar estado (ACTIVO <-> INACTIVO)
    public Estudiante cambiarEstadoEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        
        EstadoUsuario nuevoEstado = (estudiante.getEstado() == EstadoUsuario.ACTIVO)
            ? EstadoUsuario.INACTIVO
            : EstadoUsuario.ACTIVO;
        
        estudiante.setEstado(nuevoEstado);
        return estudianteRepository.save(estudiante);
    }
    
    // Eliminación lógica (solo cambia estado)
    public void eliminarEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        
        estudiante.setEstado(EstadoUsuario.INACTIVO);
        estudianteRepository.save(estudiante);
    }
}

