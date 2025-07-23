package com.udemy.matriculas.eventos.services;

import com.udemy.matriculas.eventos.models.dtos.EventoDTO;
import com.udemy.matriculas.eventos.models.entities.Eventos;
import com.udemy.matriculas.eventos.models.enums.EstadoEvento;
import com.udemy.matriculas.eventos.repositories.EventoRepository;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.repositories.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final DocenteRepository docenteRepository;

    @Transactional(readOnly = true)
    public List<Eventos> listarEventos() {
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Eventos obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
    }

    @Transactional
    public Eventos registrarEvento(EventoDTO dto) {
        Eventos evento = new Eventos();
        evento.setNombre(dto.getNombre());
        evento.setPrecioEntrada(dto.getPrecioEntrada());
        evento.setFechaInicio(dto.getFechaInicio());
        evento.setFechaFin(dto.getFechaFin());
        evento.setCapacidadMaxima(dto.getCapacidadMaxima());
        evento.setDescripcion(dto.getDescripcion());
        evento.setEstado(EstadoEvento.PROXIMO);

        if (dto.getDocenteId() != null) {
            Docente docente = docenteRepository.findById(dto.getDocenteId())
                    .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
            evento.setDocente(docente);
        }

        return eventoRepository.save(evento);
    }

    @Transactional
    public Eventos actualizarEvento(Long id, EventoDTO dto) {
        Eventos evento = obtenerEventoPorId(id);
        evento.setNombre(dto.getNombre());
        evento.setPrecioEntrada(dto.getPrecioEntrada());
        evento.setFechaInicio(dto.getFechaInicio());
        evento.setFechaFin(dto.getFechaFin());
        evento.setCapacidadMaxima(dto.getCapacidadMaxima());
        evento.setDescripcion(dto.getDescripcion());

        if (dto.getDocenteId() != null) {
            Docente docente = docenteRepository.findById(dto.getDocenteId())
                    .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
            evento.setDocente(docente);
        } else {
            evento.setDocente(null);
        }

        return eventoRepository.save(evento);
    }

    @Transactional
    public void eliminarEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Evento no encontrado con ID: " + id);
        }
        eventoRepository.deleteById(id);
    }

    public long contarEventos() {
        return eventoRepository.count();
    }
}
