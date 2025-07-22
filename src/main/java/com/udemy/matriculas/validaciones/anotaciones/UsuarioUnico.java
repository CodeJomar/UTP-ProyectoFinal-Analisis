package com.udemy.matriculas.validaciones.anotaciones;

import com.udemy.matriculas.validaciones.UsuarioUnicoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsuarioUnicoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsuarioUnico {
    String message() default "Este correo ya está registrado...";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
