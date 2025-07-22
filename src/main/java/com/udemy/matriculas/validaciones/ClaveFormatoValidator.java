package com.udemy.matriculas.validaciones;

import com.udemy.matriculas.validaciones.anotaciones.ClaveFormato;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClaveFormatoValidator implements ConstraintValidator<ClaveFormato, String> {
    
    private static final String REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=<>?{}\\[\\]-]).{6,}$";
    
    @Override
    public boolean isValid(String clave, ConstraintValidatorContext context) {
        if (clave == null || clave.isBlank()) return true;
        return clave.matches(REGEX);
    }
}