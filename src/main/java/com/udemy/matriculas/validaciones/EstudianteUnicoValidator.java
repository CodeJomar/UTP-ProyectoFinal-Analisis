package com.udemy.matriculas.validaciones;

import com.udemy.matriculas.registros.models.dtos.EstudianteDTO;
import com.udemy.matriculas.registros.models.entities.Estudiante;
import com.udemy.matriculas.registros.repositories.EstudianteRepository;
import com.udemy.matriculas.validaciones.anotaciones.EstudianteUnico;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EstudianteUnicoValidator implements ConstraintValidator<EstudianteUnico, EstudianteDTO> {
    
    @Autowired
    private EstudianteRepository empleadoRepository;
    
    @Override
    public boolean isValid(EstudianteDTO dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getUsername() == null || dto.getUsername().isBlank()) {
            return true;
        }
        
        Optional<Estudiante> existente = empleadoRepository.findByUsuario_Username(dto.getUsername());
        
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