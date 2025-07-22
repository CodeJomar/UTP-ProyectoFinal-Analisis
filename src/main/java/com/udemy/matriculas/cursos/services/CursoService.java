package com.udemy.matriculas.cursos.services;

import com.udemy.matriculas.cursos.models.dtos.CursoDTO;
import com.udemy.matriculas.cursos.models.entities.Cursos;
import com.udemy.matriculas.cursos.models.enums.EstadoCurso;
import com.udemy.matriculas.cursos.repositories.CursoRepository;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.repositories.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final DocenteRepository docenteRepository;

    @Transactional(readOnly = true)
    public List<Cursos> listarCursos() {
        return cursoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cursos obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
    }

    @Transactional
    public Cursos registrarCurso(CursoDTO dto) {
        Docente docente = docenteRepository.findById(dto.getDocenteId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        Cursos curso = new Cursos();
        curso.setNombre(dto.getNombre());
        curso.setPrecio(dto.getPrecio());
        curso.setFechaInicio(dto.getFechaInicio());
        curso.setFechaFin(dto.getFechaFin());
        curso.setCapacidadMaxima(dto.getCapacidadMaxima());
        curso.setDescripcion(dto.getDescripcion());
        curso.setPreRequisito(dto.getPreRequisito());
        curso.setEstado(EstadoCurso.EN_CURSO);
        curso.setDocente(docente);

        return cursoRepository.save(curso);
    }

    @Transactional
    public Cursos actualizarCurso(Long id, CursoDTO dto) {
        Cursos curso = obtenerCursoPorId(id);
        Docente docente = docenteRepository.findById(dto.getDocenteId())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        curso.setNombre(dto.getNombre());
        curso.setPrecio(dto.getPrecio());
        curso.setFechaInicio(dto.getFechaInicio());
        curso.setFechaFin(dto.getFechaFin());
        curso.setCapacidadMaxima(dto.getCapacidadMaxima());
        curso.setDescripcion(dto.getDescripcion());
        curso.setPreRequisito(dto.getPreRequisito());
        curso.setDocente(docente);

        return cursoRepository.save(curso);
    }
}