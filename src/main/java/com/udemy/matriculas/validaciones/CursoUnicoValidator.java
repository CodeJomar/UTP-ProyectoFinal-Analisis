package com.udemy.matriculas.validaciones;

import com.udemy.matriculas.cursos.models.dtos.CursoDTO;
import com.udemy.matriculas.cursos.repositories.CursoRepository;
import com.udemy.matriculas.validaciones.anotaciones.CursoUnico;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CursoUnicoValidator implements ConstraintValidator<CursoUnico, CursoDTO> {

    private final CursoRepository cursoRepository;

    @Override
    public boolean isValid(CursoDTO dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().isBlank()) {
            return true;
        }

        return cursoRepository.findByNombre(dto.getNombre())
                .map(cursoExistente -> {
                    boolean esElMismo = dto.getId() != null && cursoExistente.getId().equals(dto.getId());
                    if (!esElMismo) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                                .addPropertyNode("nombre")
                                .addConstraintViolation();
                    }
                    return esElMismo;
                })
                .orElse(true);
    }
}