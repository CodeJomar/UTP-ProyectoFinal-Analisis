package com.udemy.matriculas.registros.controllers;

import com.udemy.matriculas.registros.models.dtos.DocenteDTO;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.services.DocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios/docentes")
@RequiredArgsConstructor
public class DocenteController {
    
    private final DocenteService docenteService;
    
    // Página principal
    @GetMapping("")
    public String paginaDocentes(Model model) {
        etiquetasThymeleaf(model, new DocenteDTO());
        return "docentes";
    }
    
    // Guardar o actualizar
    @PostMapping("/guardar")
    public String guardarDocente(@ModelAttribute("docente") DocenteDTO dto, Model model) {
        if (dto.getId() != null) {
            docenteService.actualizarDocente(dto.getId(), dto);
        } else {
            docenteService.registrarDocente(dto);
        }
        return "redirect:/usuarios/docentes";
    }
    
    // Editar: cargar datos del estudiante
    @GetMapping("/editar/{id}")
    public String editarDocente(@PathVariable Long id, Model model) {
        Docente docente = docenteService.obtenerPorId(id);
        DocenteDTO dto = convertirADocenteDTO(docente);
        etiquetasThymeleaf(model, dto);
        return "docentes";
    }
    
    // Cambiar estado (ACTIVO <-> INACTIVO)
    @GetMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id) {
        docenteService.cambiarEstadoDocente(id);
        return "redirect:/usuarios/docentes";
    }
    
    // Eliminar (opcional)
    @GetMapping("/eliminar/{id}")
    public String eliminarDocente(@PathVariable Long id) {
        docenteService.eliminarDocente(id);
        return "redirect:/usuarios/docentes";
    }
    
    private void etiquetasThymeleaf(Model model, DocenteDTO dto) {
        model.addAttribute("docente", dto);
        model.addAttribute("docentes", docenteService.listarDocentes());
    }
    
    private DocenteDTO convertirADocenteDTO(Docente docente) {
        return new DocenteDTO(
            docente.getId(),
            docente.getUsuario().getUsername(),
            docente.getUsuario().getPassword(),
            docente.getNombre(),
            docente.getApellido(),
            docente.getCelular(),
            docente.getDni(),
            docente.getEspecialidad(),
            docente.getSalario(),
            docente.getEstado(),
            docente.getUsuario().getRol()
        );
    }
}

