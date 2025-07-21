package com.udemy.matriculas.eventos.models.entities;

import com.udemy.matriculas.eventos.models.enums.EstadoEvento;
import com.udemy.matriculas.registros.models.entities.Docente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_evento", nullable = false, length = 150)
    private String nombre;

    @Column(name = "precio_entrada")
    private BigDecimal precioEntrada;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @Column(name = "capacidad_maxima")
    private Integer capacidadMaxima;

    @Column(length = 500)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_evento", nullable = false)
    private EstadoEvento estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id")
    private Docente docente;
}