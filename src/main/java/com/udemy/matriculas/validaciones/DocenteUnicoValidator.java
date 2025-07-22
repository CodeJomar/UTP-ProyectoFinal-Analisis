package com.udemy.matriculas.validaciones;

import com.udemy.matriculas.registros.models.dtos.DocenteDTO;
import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.repositories.DocenteRepository;
import com.udemy.matriculas.validaciones.anotaciones.DocenteUnico;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DocenteUnicoValidator implements ConstraintValidator<DocenteUnico, DocenteDTO> {
    
    @Autowired
    private DocenteRepository empleadoRepository;
    
    @Override
    public boolean isValid(DocenteDTO dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getUsername() == null || dto.getUsername().isBlank()) {
            return true;
        }
        
        Optional<Docente> existente = empleadoRepository.findByUsuario_Username(dto.getUsername());
        
        if (empleadoRepository == null) {
            return true;
        }
        
        if (existente.isPresent()) {
            Long idExistente = existente.get().getId();         // id del empleado encontrado
            Long idActual = dto.getId();                        // id del empleado en edición
            
            boolean esElMismo = idActual != null && idExistente.equals(idActual);
            
            if (!esElMismo) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("username")
                    .addConstraintViolation();
            }
            return esElMismo;
        }
        
        return true; // No existe ningún empleado con ese email
    }
}