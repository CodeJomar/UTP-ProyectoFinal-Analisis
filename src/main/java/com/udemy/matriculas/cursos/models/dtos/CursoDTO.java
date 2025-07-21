package com.udemy.matriculas.cursos.models.dtos;

import com.udemy.matriculas.cursos.models.enums.EstadoCurso;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {

    private Long id;
    private String nombre;
    private BigDecimal precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer capacidadMaxima;
    private String descripcion;
    private String preRequisito;
    private EstadoCurso estado;
    private Long docenteId;
    private String nombreDocente;
}
