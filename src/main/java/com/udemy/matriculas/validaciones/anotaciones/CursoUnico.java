package com.udemy.matriculas.validaciones.anotaciones;

import com.udemy.matriculas.validaciones.CursoUnicoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CursoUnicoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CursoUnico {
    String message() default "Ya existe un curso con este nombre.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}