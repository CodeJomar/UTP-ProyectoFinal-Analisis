package com.udemy.matriculas.eventos.controller;

import com.udemy.matriculas.eventos.models.dtos.EventoDTO;
import com.udemy.matriculas.eventos.models.entities.Eventos;
import com.udemy.matriculas.eventos.services.EventoService;
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
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;
    private final DocenteService docenteService;

    @GetMapping
    public String paginaEventos(Model model) {
        if (!model.containsAttribute("evento")) {
            model.addAttribute("evento", new EventoDTO());
        }
        if (!model.containsAttribute("eventoParaCrear")) {
            model.addAttribute("eventoParaCrear", new EventoDTO());
        }
        cargarAtributosComunes(model);
        return "eventos";
    }

    @PostMapping("/guardar")
    public String guardarEvento(@Valid @ModelAttribute("evento") EventoDTO dto, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            String objectName = dto.getId() == null ? "eventoParaCrear" : "evento";
            redirect.addFlashAttribute(objectName, dto);
            redirect.addFlashAttribute("org.springframework.validation.BindingResult." + objectName, result);
            if (dto.getId() != null) {
                redirect.addFlashAttribute("openModal", true);
            }
            return "redirect:/eventos";
        }

        try {
            if (dto.getId() == null) {
                eventoService.registrarEvento(dto);
                redirect.addFlashAttribute("successMessage", "¡Evento registrado exitosamente!");
            } else {
                eventoService.actualizarEvento(dto.getId(), dto);
                redirect.addFlashAttribute("successMessage", "¡Evento actualizado exitosamente!");
            }
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/eventos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            Eventos evento = eventoService.obtenerEventoPorId(id);
            EventoDTO dto = convertirAEventoDTO(evento);
            redirect.addFlashAttribute("evento", dto);
            redirect.addFlashAttribute("openModal", true);
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/eventos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            eventoService.eliminarEvento(id);
            redirect.addFlashAttribute("successMessage", "Evento eliminado exitosamente.");
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/eventos";
    }

    private void cargarAtributosComunes(Model model) {
        List<EventoDTO> eventos = eventoService.listarEventos().stream()
                .map(this::convertirAEventoDTO)
                .collect(Collectors.toList());
        List<Docente> docentes = docenteService.listarDocentes();
        model.addAttribute("eventos", eventos);
        model.addAttribute("docentesDisponibles", docentes);
    }

    private EventoDTO convertirAEventoDTO(Eventos evento) {
        Long docenteId = (evento.getDocente() != null) ? evento.getDocente().getId() : null;
        String nombreDocente = (evento.getDocente() != null) ? evento.getDocente().getNombre() + " " + evento.getDocente().getApellido() : "N/A";
        return new EventoDTO(evento.getId(), evento.getNombre(), evento.getPrecioEntrada(), evento.getFechaInicio(), evento.getFechaFin(), evento.getCapacidadMaxima(), evento.getDescripcion(), evento.getEstado(), docenteId, nombreDocente);
    }
}