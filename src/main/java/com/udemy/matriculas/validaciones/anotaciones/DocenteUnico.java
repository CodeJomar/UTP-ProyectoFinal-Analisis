package com.udemy.matriculas.validaciones.anotaciones;

import com.udemy.matriculas.validaciones.DocenteUnicoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DocenteUnicoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DocenteUnico {
    String message() default "Este correo para docentes ya está registrado...";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
