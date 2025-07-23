package com.udemy.matriculas.auth.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RolList {
    ROLE_ADMIN("Administrador", "text-bg-danger"),
    ROLE_STUDENT("Estudiante", "text-bg-primary"),
    ROLE_TEACHER("Docente", "text-bg-success");
    
    private final String displayName;
    private final String cssClass;
}
