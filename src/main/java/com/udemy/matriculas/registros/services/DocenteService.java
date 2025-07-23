package com.udemy.matriculas.registros.services;

import com.udemy.matriculas.auth.models.entities.Rol;
import com.udemy.matriculas.auth.models.entities.Usuario;
import com.udemy.matriculas.auth.models.enums.RolList;
import com.udemy.matriculas.auth.repositories.RolRepository;
import com.udemy.matriculas.auth.repositories.UsuarioRepository;
import com.udemy.matriculas.registros.models.dtos.DocenteDTO;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import com.udemy.matriculas.registros.repositories.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<Docente> listarDocentes() {
        return docenteRepository.findAll();
    }

    @Transactional
    public Docente registrarDocente(DocenteDTO dto) {
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está en uso.");
        }

        Rol rolTeacher = rolRepository.findByNombre(RolList.ROLE_TEACHER)
                .orElseThrow(() -> new RuntimeException("Error: No se encontró el rol de docente."));

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
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

    @Transactional
    public Docente actualizarDocente(Long id, DocenteDTO dto) {
        Docente docente = obtenerPorId(id);

        if (!docente.getUsuario().getUsername().equals(dto.getUsername())) {
            if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
                throw new IllegalArgumentException("El nuevo correo ya está en uso.");
            }
            docente.getUsuario().setUsername(dto.getUsername());
        }

        docente.setNombre(dto.getNombre());
        docente.setApellido(dto.getApellido());
        docente.setCelular(dto.getCelular());
        docente.setDni(dto.getDni());
        docente.setEspecialidad(dto.getEspecialidad());
        docente.setSalario(dto.getSalario());

        return docenteRepository.save(docente);
    }

    @Transactional
    public void cambiarEstadoDocente(Long id) {
        Docente docente = obtenerPorId(id);
        EstadoUsuario nuevoEstado = (docente.getEstado() == EstadoUsuario.ACTIVO)
                ? EstadoUsuario.INACTIVO
                : EstadoUsuario.ACTIVO;
        docente.setEstado(nuevoEstado);
        docenteRepository.save(docente);
    }

    @Transactional(readOnly = true)
    public Docente obtenerPorId(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));
    }

    public long contarDocentes() {
        return docenteRepository.count();
    }
    
    public long contarDocentes() {
        return docenteRepository.count();
    }
    
}
