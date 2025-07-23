package com.udemy.matriculas.cursos.models.dtos;

import com.udemy.matriculas.cursos.models.enums.EstadoCurso;
import com.udemy.matriculas.validaciones.anotaciones.CursoUnico;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CursoUnico
public class CursoDTO {

    private Long id;

    @NotBlank(message = "El nombre del curso es obligatorio")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser un número positivo")
    private BigDecimal precio;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaFin;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser un número positivo")
    private Integer capacidadMaxima;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    private String preRequisito;

    private EstadoCurso estado;

    @NotNull(message = "Debe seleccionar un docente")
    private Long docenteId;

    private String nombreDocente;
}