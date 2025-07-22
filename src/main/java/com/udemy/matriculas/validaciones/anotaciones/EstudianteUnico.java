package com.udemy.matriculas.validaciones.anotaciones;

import com.udemy.matriculas.validaciones.EstudianteUnicoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EstudianteUnicoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EstudianteUnico {
    String message() default "Este correo para estudiantes ya está registrado...";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
