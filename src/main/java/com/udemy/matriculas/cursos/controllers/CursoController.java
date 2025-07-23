package com.udemy.matriculas.cursos.controllers;

import com.udemy.matriculas.cursos.models.dtos.CursoDTO;
import com.udemy.matriculas.cursos.models.entities.Cursos;
import com.udemy.matriculas.cursos.services.CursoService;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.services.DocenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;
    private final DocenteService docenteService;

    @GetMapping
    public String paginaCursos(Model model) {
        if (!model.containsAttribute("curso")) {
            model.addAttribute("curso", new CursoDTO());
        }
        if (!model.containsAttribute("cursoParaCrear")) {
            model.addAttribute("cursoParaCrear", new CursoDTO());
        }
        etiquetasThymeleaf(model);
        return "cursos";
    }

    @PostMapping("/guardar")
    public String guardarCurso(@Valid @ModelAttribute("curso") CursoDTO cursoDTO, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            String objectName = cursoDTO.getId() == null ? "cursoParaCrear" : "curso";
            redirect.addFlashAttribute(objectName, cursoDTO);
            redirect.addFlashAttribute("org.springframework.validation.BindingResult." + objectName, result);
            if (cursoDTO.getId() != null) {
                redirect.addFlashAttribute("openModal", true);
            }
            return "redirect:/cursos";
        }

        if (cursoDTO.getId() == null) {
            cursoService.registrarCurso(cursoDTO);
            redirect.addFlashAttribute("successMessage", "¡Curso registrado exitosamente!");
        } else {
            cursoService.actualizarCurso(cursoDTO.getId(), cursoDTO);
            redirect.addFlashAttribute("successMessage", "¡Curso actualizado exitosamente!");
        }
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            Cursos curso = cursoService.obtenerCursoPorId(id);
            CursoDTO cursoDTO = convertirACursoDTO(curso);
            redirect.addFlashAttribute("curso", cursoDTO);
            redirect.addFlashAttribute("openModal", true);
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            cursoService.eliminarCurso(id);
            redirect.addFlashAttribute("successMessage", "Curso eliminado exitosamente.");
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/cursos";
    }

    private void etiquetasThymeleaf(Model model) {
        List<CursoDTO> cursos = cursoService.listarCursos().stream()
                .map(this::convertirACursoDTO)
                .collect(Collectors.toList());
        List<Docente> docentes = docenteService.listarDocentes();
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