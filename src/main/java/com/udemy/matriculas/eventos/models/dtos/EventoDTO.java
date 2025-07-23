package com.udemy.matriculas.eventos.models.dtos;

import com.udemy.matriculas.eventos.models.enums.EstadoEvento;
import com.udemy.matriculas.validaciones.anotaciones.EventoUnico;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EventoUnico
public class EventoDTO {

    private Long id;

    @NotBlank(message = "El nombre del evento es obligatorio")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio no puede ser negativo")
    private BigDecimal precioEntrada;

    @NotNull(message = "La fecha y hora de inicio son obligatorias")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha y hora de fin son obligatorias")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaFin;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser un número positivo")
    private Integer capacidadMaxima;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    private EstadoEvento estado;

    private Long docenteId;

    private String nombreDocente;
}