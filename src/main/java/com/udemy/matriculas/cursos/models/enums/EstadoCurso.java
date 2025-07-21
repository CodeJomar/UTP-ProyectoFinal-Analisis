package com.udemy.matriculas.cursos.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoCurso {
    EN_CURSO("En Curso"),
    FINALIZADO("Finalizado");

    private final String displayName;
}