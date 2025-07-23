package com.udemy.matriculas.validaciones;

import com.udemy.matriculas.eventos.models.dtos.EventoDTO;
import com.udemy.matriculas.eventos.repositories.EventoRepository;
import com.udemy.matriculas.validaciones.anotaciones.EventoUnico;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventoUnicoValidator implements ConstraintValidator<EventoUnico, EventoDTO> {

    private final EventoRepository eventoRepository;

    @Override
    public boolean isValid(EventoDTO dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().isBlank()) {
            return true;
        }

        return eventoRepository.findByNombre(dto.getNombre())
                .map(eventoExistente -> {
                    boolean esElMismo = dto.getId() != null && eventoExistente.getId().equals(dto.getId());
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