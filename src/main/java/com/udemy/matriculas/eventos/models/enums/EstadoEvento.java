package com.udemy.matriculas.eventos.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoEvento {
    PROXIMO("Próximo"),
    FINALIZADO("Finalizado");

    private final String displayName;
}