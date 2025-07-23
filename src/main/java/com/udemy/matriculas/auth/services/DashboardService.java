package com.udemy.matriculas.auth.services;

import com.udemy.matriculas.auth.models.dtos.ActividadRecienteDTO;
import com.udemy.matriculas.cursos.models.entities.Cursos;
import com.udemy.matriculas.cursos.repositories.CursoRepository;
import com.udemy.matriculas.eventos.models.entities.Eventos;
import com.udemy.matriculas.eventos.repositories.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CursoRepository cursoRepository;
    private final EventoRepository eventoRepository;

    public List<ActividadRecienteDTO> getActividadesRecientes() {
        List<ActividadRecienteDTO> actividades = new ArrayList<>();

        List<Cursos> cursos = cursoRepository.findAll();
        for (Cursos curso : cursos) {
            String descripcion = "Por " + curso.getDocente().getNombre() + " " + curso.getDocente().getApellido();
            actividades.add(new ActividadRecienteDTO(
                    "Curso",
                    curso.getNombre() + " - creado",
                    descripcion,
                    curso.getFechaInicio().atStartOfDay(),
                    "bi bi-book",
                    "bg-success",
                    "Curso"
            ));
        }

        List<Eventos> eventos = eventoRepository.findAll();
        for (Eventos evento : eventos) {
            String responsable = (evento.getDocente() != null)
                    ? "Por " + evento.getDocente().getNombre() + " " + evento.getDocente().getApellido()
                    : "Sistema";
            actividades.add(new ActividadRecienteDTO(
                    "Evento",
                    evento.getNombre() + " - programado",
                    responsable,
                    evento.getFechaInicio(),
                    "bi bi-calendar-event",
                    "bg-info",
                    "Evento"
            ));
        }

        actividades.sort(Comparator.comparing(ActividadRecienteDTO::getFechaHora).reversed());

        return actividades.stream().limit(5).collect(Collectors.toList());
    }
}