package com.udemy.matriculas.registros.models.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoUsuario {
    
    ACTIVO("Activo", ""),
    INACTIVO("Inactivo", "");
    
    private final String displayName;
    private final String cssClass;
    
}
