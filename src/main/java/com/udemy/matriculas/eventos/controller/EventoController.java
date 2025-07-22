package com.udemy.matriculas.eventos.controller;

import com.udemy.matriculas.eventos.models.dtos.EventoDTO;
import com.udemy.matriculas.eventos.models.entities.Eventos;
import com.udemy.matriculas.eventos.services.EventoService;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.services.DocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
/*
@Controller
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;
    private final DocenteService docenteService;

    // Página principal
    @GetMapping
    public String paginaEventos(Model model) {
        etiquetasThymeleaf(model, new EventoDTO());
        return "eventos";
    }

    // Guardar o actualizar
    @PostMapping("/guardar")
    public String guardarEvento(@ModelAttribute("evento") EventoDTO dto) {
        if (dto.getId() == null) {
            eventoService.registrarEvento(dto);
        } else {
            eventoService.actualizarEvento(dto.getId(), dto);
        }
        return "redirect:/eventos";
    }

    // Cargar datos para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Eventos evento = eventoService.obtenerEventoPorId(id);
        EventoDTO dto = convertirAEventoDTO(evento);
        etiquetasThymeleaf(model, dto);
        return "eventos";
    }

    // ---- Métodos de ayuda ----
    private void etiquetasThymeleaf(Model model, EventoDTO eventoDTO) {
        List<EventoDTO> eventos = eventoService.listarEventos().stream()
                .map(this::convertirAEventoDTO)
                .collect(Collectors.toList());
        List<Docente> docentes = docenteService.listarDocentesActivos();

        model.addAttribute("evento", eventoDTO);
        model.addAttribute("eventos", eventos);
        model.addAttribute("docentesDisponibles", docentes);
    }

    private EventoDTO convertirAEventoDTO(Eventos evento) {
        Long docenteId = (evento.getDocente() != null) ? evento.getDocente().getId() : null;
        String nombreDocente = (evento.getDocente() != null) ? evento.getDocente().getNombre() + " " + evento.getDocente().getApellido() : "N/A";

        return new EventoDTO(
                evento.getId(),
                evento.getNombre(),
                evento.getPrecioEntrada(),
                evento.getFechaInicio(),
                evento.getFechaFin(),
                evento.getCapacidadMaxima(),
                evento.getDescripcion(),
                evento.getEstado(),
                docenteId,
                nombreDocente
        );
    }
}
*/