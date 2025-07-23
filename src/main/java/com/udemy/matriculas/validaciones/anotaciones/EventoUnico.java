package com.udemy.matriculas.validaciones.anotaciones;

import com.udemy.matriculas.validaciones.EventoUnicoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EventoUnicoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventoUnico {
    String message() default "Ya existe un evento con este nombre.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}