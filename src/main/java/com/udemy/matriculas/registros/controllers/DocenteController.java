package com.udemy.matriculas.registros.controllers;

import com.udemy.matriculas.registros.models.dtos.DocenteDTO;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.services.DocenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios/docentes")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService docenteService;

    private void cargarAtributosComunes(Model model) {
        model.addAttribute("docentes", docenteService.listarDocentes());
    }

    @GetMapping("")
    public String paginaDocentes(Model model) {
        if (!model.containsAttribute("docente")) {
            model.addAttribute("docente", new DocenteDTO());
        }
        cargarAtributosComunes(model);
        return "docentes";
    }

    @PostMapping("/guardar")
    public String guardarDocente(@Valid @ModelAttribute("docente") DocenteDTO dto,
                                 BindingResult result, RedirectAttributes redirect) {
        if (dto.getId() == null && (dto.getPassword() == null || dto.getPassword().isBlank())) {
            result.rejectValue("password", "NotBlank", "La contraseña es obligatoria para nuevos docentes");
        }

        if (result.hasErrors()) {
            redirect.addFlashAttribute("docente", dto);
            redirect.addFlashAttribute("org.springframework.validation.BindingResult.docente", result);
            return "redirect:/usuarios/docentes";
        }

        try {
            if (dto.getId() == null) {
                docenteService.registrarDocente(dto);
                redirect.addFlashAttribute("successMessage", "Docente registrado exitosamente.");
            } else {
                docenteService.actualizarDocente(dto.getId(), dto);
                redirect.addFlashAttribute("successMessage", "Docente actualizado exitosamente.");
            }
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("docente", dto);
            redirect.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/usuarios/docentes";
    }

    @GetMapping("/editar/{id}")
    public String editarDocente(@PathVariable Long id, RedirectAttributes redirect) {
        Docente docente = docenteService.obtenerPorId(id);
        DocenteDTO dto = new DocenteDTO();
        dto.setId(docente.getId());
        dto.setUsername(docente.getUsuario().getUsername());
        dto.setNombre(docente.getNombre());
        dto.setApellido(docente.getApellido());
        dto.setCelular(docente.getCelular());
        dto.setDni(docente.getDni());
        dto.setEspecialidad(docente.getEspecialidad());
        dto.setSalario(docente.getSalario());
        dto.setEstado(docente.getEstado());

        redirect.addFlashAttribute("docente", dto);
        return "redirect:/usuarios/docentes";
    }

    @GetMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id, RedirectAttributes redirect) {
        docenteService.cambiarEstadoDocente(id);
        redirect.addFlashAttribute("successMessage", "Estado del docente cambiado exitosamente.");
        return "redirect:/usuarios/docentes";
    }
}