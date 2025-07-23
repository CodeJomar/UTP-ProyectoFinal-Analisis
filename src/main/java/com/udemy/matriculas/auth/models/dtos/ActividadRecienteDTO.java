package com.udemy.matriculas.auth.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ActividadRecienteDTO {
    private String tipo;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaHora;
    private String iconoCssClass;
    private String tagColorClass;
    private String textoTag;
}