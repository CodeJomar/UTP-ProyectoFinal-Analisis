package com.udemy.matriculas.validaciones.anotaciones;

import com.udemy.matriculas.validaciones.ClaveFormatoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ClaveFormatoValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClaveFormato {
    String message() default "La contraseña debe contener: 6 caracteres, una Mayúscula, un Número y un Carácter Especial";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
