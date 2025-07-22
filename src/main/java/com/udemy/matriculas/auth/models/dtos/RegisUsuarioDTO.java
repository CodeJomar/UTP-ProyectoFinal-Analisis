package com.udemy.matriculas.auth.models.dtos;

import com.udemy.matriculas.validaciones.anotaciones.ClaveFormato;
import com.udemy.matriculas.validaciones.anotaciones.UsuarioUnico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UsuarioUnico
public class RegisUsuarioDTO {
    
    private String id;
    
    @NotBlank(message = "Este campo es obligatorio...")
    @Email(message = "Formato de correo inválido...")
    private String username;
    
    @NotBlank(message = "Este campo es obligatorio...")
    @ClaveFormato
    private String password;
    
}
