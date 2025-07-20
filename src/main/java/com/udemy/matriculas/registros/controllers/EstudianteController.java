package com.udemy.matriculas.registros.controllers;

import com.udemy.matriculas.registros.models.dtos.EstudianteDTO;
import com.udemy.matriculas.registros.models.entities.Estudiante;
import com.udemy.matriculas.registros.services.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {
    
    private final EstudianteService estudianteService;
    
    // Página principal
    @GetMapping("")
    public String paginaEstudiantes(Model model) {
        etiquetasThymeleaf(model, new EstudianteDTO());
        return "estudiantes";
    }
    
    // Guardar o actualizar
    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute("estudiante") EstudianteDTO dto, Model model) {
        if (dto.getId() != null) {
            estudianteService.actualizarEstudiante(dto.getId(), dto);
        } else {
            estudianteService.registrarEstudiante(dto);
        }
        return "redirect:/usuarios/estudiantes";
    }
    
    // Editar: cargar datos del estudiante
    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteService.obtenerPorId(id);
        EstudianteDTO dto = convertirAEstudianteDTO(estudiante);
        etiquetasThymeleaf(model, dto);
        return "estudiantes";
    }
    
    // Cambiar estado (ACTIVO <-> INACTIVO)
    @GetMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id) {
        estudianteService.cambiarEstadoEstudiante(id);
        return "redirect:/usuarios/estudiantes";
    }
    
    // Eliminar (opcional)
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return "redirect:/usuarios/estudiantes";
    }
    
    private void etiquetasThymeleaf(Model model, EstudianteDTO dto) {
        model.addAttribute("estudiante", dto);
        model.addAttribute("estudiantes", estudianteService.listarEstudiantes());
    }
    
    private EstudianteDTO convertirAEstudianteDTO(Estudiante estudiante) {
        return new EstudianteDTO(
            estudiante.getId(),
            estudiante.getUsuario().getUsername(),
            estudiante.getUsuario().getPassword(),
            estudiante.getNombre(),
            estudiante.getApellido(),
            estudiante.getCelular(),
            estudiante.getDni(),
            estudiante.getEstado(),
            estudiante.getUsuario().getRol()
        );
    }
}

