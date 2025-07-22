package com.udemy.matriculas.validaciones;

import com.udemy.matriculas.auth.models.dtos.RegisUsuarioDTO;
import com.udemy.matriculas.auth.models.entities.Usuario;
import com.udemy.matriculas.auth.repositories.UsuarioRepository;
import com.udemy.matriculas.validaciones.anotaciones.UsuarioUnico;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioUnicoValidator implements ConstraintValidator<UsuarioUnico, RegisUsuarioDTO> {
    
    @Autowired
    private UsuarioRepository userRepository;
    
    @Override
    public boolean isValid(RegisUsuarioDTO usuario, ConstraintValidatorContext context) {
        if (usuario == null || usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            return true;
        }
        
        String username = usuario.getUsername();
        String userId = usuario.getId();
        
        Optional<Usuario> usuarioExistente = userRepository.findByUsername(username);
        
        if (userRepository == null) {
            return true;
        }
        
        if (usuarioExistente.isPresent()) {
            boolean esMismoUsuario = userId != null && usuarioExistente.get().getId().equals(userId);
            if (!esMismoUsuario) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context
                        .getDefaultConstraintMessageTemplate())
                    .addPropertyNode("username")
                    .addConstraintViolation();
            }
            return esMismoUsuario;
        }
        return true;
    }
}
