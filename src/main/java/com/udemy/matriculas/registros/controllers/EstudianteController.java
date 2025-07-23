package com.udemy.matriculas.registros.controllers;

import com.udemy.matriculas.registros.models.dtos.EstudianteDTO;
import com.udemy.matriculas.registros.models.entities.Estudiante;
import com.udemy.matriculas.registros.services.EstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios/alumnos")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    private void cargarAtributosComunes(Model model) {
        model.addAttribute("estudiantes", estudianteService.listarEstudiantes());
    }

    @GetMapping
    public String paginaEstudiantes(Model model) {
        if (!model.containsAttribute("estudiante")) {
            model.addAttribute("estudiante", new EstudianteDTO());
        }
        cargarAtributosComunes(model);
        return "alumnos";
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@Valid @ModelAttribute("estudiante") EstudianteDTO dto,
                                    BindingResult result, RedirectAttributes redirect) {
        if (dto.getId() == null && (dto.getPassword() == null || dto.getPassword().isBlank())) {
            result.rejectValue("password", "NotBlank", "La contraseña es obligatoria para nuevos estudiantes");
        }

        if (result.hasErrors()) {
            redirect.addFlashAttribute("estudiante", dto);
            redirect.addFlashAttribute("org.springframework.validation.BindingResult.estudiante", result);
            return "redirect:/usuarios/alumnos";
        }

        try {
            if (dto.getId() == null) {
                estudianteService.registrarEstudiante(dto);
                redirect.addFlashAttribute("successMessage", "Estudiante registrado exitosamente.");
            } else {
                estudianteService.actualizarEstudiante(dto.getId(), dto);
                redirect.addFlashAttribute("successMessage", "Estudiante actualizado exitosamente.");
            }
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("estudiante", dto);
            redirect.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/usuarios/alumnos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, RedirectAttributes redirect) {
        Estudiante estudiante = estudianteService.obtenerPorId(id);
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(estudiante.getId());
        dto.setUsername(estudiante.getUsuario().getUsername());
        dto.setNombre(estudiante.getNombre());
        dto.setApellido(estudiante.getApellido());
        dto.setCelular(estudiante.getCelular());
        dto.setDni(estudiante.getDni());
        dto.setEstado(estudiante.getEstado());

        redirect.addFlashAttribute("estudiante", dto);
        return "redirect:/usuarios/alumnos";
    }

    @GetMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id, RedirectAttributes redirect) {
        estudianteService.cambiarEstadoEstudiante(id);
        redirect.addFlashAttribute("successMessage", "Estado del estudiante cambiado exitosamente.");
        return "redirect:/usuarios/alumnos";
    }
}