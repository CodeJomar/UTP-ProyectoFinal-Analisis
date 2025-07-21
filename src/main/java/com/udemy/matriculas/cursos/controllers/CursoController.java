package com.udemy.matriculas.cursos.controllers;

import com.udemy.matriculas.cursos.models.dtos.CursoDTO;
import com.udemy.matriculas.cursos.models.entities.Cursos;
import com.udemy.matriculas.cursos.services.CursoService;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.services.DocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;
    private final DocenteService docenteService;

    // Página principal
    @GetMapping
    public String paginaCursos(Model model) {
        etiquetasThymeleaf(model, new CursoDTO());
        return "cursos";
    }

    // Guardar o actualizar
    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute("curso") CursoDTO cursoDTO) {
        if (cursoDTO.getId() == null) {
            cursoService.registrarCurso(cursoDTO);
        } else {
            cursoService.actualizarCurso(cursoDTO.getId(), cursoDTO);
        }
        return "redirect:/cursos";
    }

    // Cargar datos para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Cursos curso = cursoService.obtenerCursoPorId(id);
        CursoDTO cursoDTO = convertirACursoDTO(curso);
        etiquetasThymeleaf(model, cursoDTO);
        return "cursos";
    }

    // ---- Métodos de ayuda ----
    private void etiquetasThymeleaf(Model model, CursoDTO cursoDTO) {
        List<CursoDTO> cursos = cursoService.listarCursos().stream()
                .map(this::convertirACursoDTO)
                .collect(Collectors.toList());
        List<Docente> docentes = docenteService.listarDocentesActivos();

        model.addAttribute("curso", cursoDTO);
        model.addAttribute("cursos", cursos);
        model.addAttribute("docentesDisponibles", docentes);
    }

    private CursoDTO convertirACursoDTO(Cursos curso) {
        return new CursoDTO(
                curso.getId(),
                curso.getNombre(),
                curso.getPrecio(),
                curso.getFechaInicio(),
                curso.getFechaFin(),
                curso.getCapacidadMaxima(),
                curso.getDescripcion(),
                curso.getPreRequisito(),
                curso.getEstado(),
                curso.getDocente().getId(),
                curso.getDocente().getNombre() + " " + curso.getDocente().getApellido()
        );
    }
}
