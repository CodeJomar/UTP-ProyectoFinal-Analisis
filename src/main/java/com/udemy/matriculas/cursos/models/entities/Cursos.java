package com.udemy.matriculas.cursos.models.entities;

import com.udemy.matriculas.cursos.models.enums.EstadoCurso;
import com.udemy.matriculas.registros.models.entities.Docente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_curso", nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "capacidad_maxima")
    private Integer capacidadMaxima;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "pre_requisito", length = 255)
    private String preRequisito;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_curso", nullable = false)
    private EstadoCurso estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id", nullable = false)
    private Docente docente;
}