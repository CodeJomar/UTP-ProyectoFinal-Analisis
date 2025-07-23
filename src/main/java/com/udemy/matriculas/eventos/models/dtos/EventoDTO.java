package com.udemy.matriculas.eventos.models.dtos;

import com.udemy.matriculas.eventos.models.enums.EstadoEvento;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {

    private Long id;
    private String nombre;
    private BigDecimal precioEntrada;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Integer capacidadMaxima;
    private String descripcion;
    private EstadoEvento estado;
    private Long docenteId;
    private String nombreDocente;
}