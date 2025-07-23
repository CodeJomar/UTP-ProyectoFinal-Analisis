package com.udemy.matriculas.auth.services;

import com.udemy.matriculas.auth.models.dtos.RegisUsuarioDTO;
import com.udemy.matriculas.auth.models.dtos.UsuarioGeneralDTO;
import com.udemy.matriculas.auth.models.entities.Rol;
import com.udemy.matriculas.auth.models.entities.Usuario;
import com.udemy.matriculas.auth.models.enums.RolList;
import com.udemy.matriculas.auth.repositories.RolRepository;
import com.udemy.matriculas.auth.repositories.UsuarioRepository;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.models.entities.Estudiante;
import com.udemy.matriculas.registros.repositories.DocenteRepository;
import com.udemy.matriculas.registros.repositories.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final DocenteRepository docenteRepository;
    private final EstudianteRepository estudianteRepository;
    private final PasswordEncoder passwordEncoder;

    public void registrarUsuario(RegisUsuarioDTO dto) {
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        Rol rolDefault = rolRepository.findByNombre(RolList.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("No se encontró el rol por defecto"));

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(rolDefault);

        usuarioRepository.save(usuario);
    }

    public List<UsuarioGeneralDTO> obtenerVistaUnificadaDeUsuarios() {
        List<Docente> docentes = docenteRepository.findAll();
        List<Estudiante> estudiantes = estudianteRepository.findAll();

        List<UsuarioGeneralDTO> listaDocentes = docentes.stream()
                .map(d -> new UsuarioGeneralDTO(
                        d.getUsuario().getId(),
                        d.getNombre() + " " + d.getApellido(),
                        d.getUsuario().getUsername(),
                        "Docente",
                        d.getCelular(),
                        d.getEstado().getDisplayName()))
                .collect(Collectors.toList());

        List<UsuarioGeneralDTO> listaEstudiantes = estudiantes.stream()
                .map(e -> new UsuarioGeneralDTO(
                        e.getUsuario().getId(),
                        e.getNombre() + " " + e.getApellido(),
                        e.getUsuario().getUsername(),
                        "Estudiante",
                        e.getCelular(),
                        e.getEstado().getDisplayName()))
                .collect(Collectors.toList());

        return Stream.concat(listaDocentes.stream(), listaEstudiantes.stream())
                .collect(Collectors.toList());
    }

    public List<Usuario> listadoUsuarios() {
        return usuarioRepository.findAll();
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