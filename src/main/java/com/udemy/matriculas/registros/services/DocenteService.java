package com.udemy.matriculas.registros.services;

import com.udemy.matriculas.auth.models.entities.Rol;
import com.udemy.matriculas.auth.models.entities.Usuario;
import com.udemy.matriculas.auth.models.enums.RolList;
import com.udemy.matriculas.registros.models.dtos.DocenteDTO;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import com.udemy.matriculas.registros.repositories.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocenteService {
    
    private final DocenteRepository docenteRepository;
    
    // Obtener todos los docentes
    public List<Docente> listarDocentes() {
        return docenteRepository.findAll();
    }
    
    // Obtener solo docentes activos
//    public List<Docente> listarDocentesActivos() {
//        return docenteRepository.findByEstadoUsuario(EstadoUsuario.ACTIVO);
//    }
    
    // Registrar un nuevo docente
    public Docente registrarDocente(DocenteDTO dto) {
        Rol rolTeacher = new Rol();
        rolTeacher.setNombre(RolList.ROLE_TEACHER);
        
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(rolTeacher);
        
        Docente docente = new Docente();
        docente.setNombre(dto.getNombre());
        docente.setApellido(dto.getApellido());
        docente.setCelular(dto.getCelular());
        docente.setDni(dto.getDni());
        docente.setEspecialidad(dto.getEspecialidad());
        docente.setSalario(dto.getSalario());
        docente.setEstado(EstadoUsuario.ACTIVO);
        docente.setUsuario(usuario);
        
        return docenteRepository.save(docente);
    }
    
    // Actualizar docente por ID
    public Docente actualizarDocente(Long id, DocenteDTO dto) {
        Docente docente = docenteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));
        
        docente.setNombre(dto.getNombre());
        docente.setApellido(dto.getApellido());
        docente.setCelular(dto.getCelular());
        docente.setDni(dto.getDni());
        docente.setEspecialidad(dto.getEspecialidad());
        docente.setSalario(dto.getSalario());
        
        return docenteRepository.save(docente);
    }
    
    // Cambiar estado del docente (Activo <-> Inactivo)
    public Docente cambiarEstadoDocente(Long id) {
        Docente docente = docenteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));
        
        EstadoUsuario nuevoEstado = (docente.getEstado() == EstadoUsuario.ACTIVO)
            ? EstadoUsuario.INACTIVO
            : EstadoUsuario.ACTIVO;
        
        docente.setEstado(nuevoEstado);
        return docenteRepository.save(docente);
    }
    
    // Eliminar lógico del docente (opcional: eliminación física también)
    public void eliminarDocente(Long id) {
        Docente docente = docenteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));
        
        docente.setEstado(EstadoUsuario.INACTIVO);
        docenteRepository.save(docente);
    }
    
    // Buscar docente por ID
    public Docente obtenerPorId(Long id) {
        return docenteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));
    }
}

