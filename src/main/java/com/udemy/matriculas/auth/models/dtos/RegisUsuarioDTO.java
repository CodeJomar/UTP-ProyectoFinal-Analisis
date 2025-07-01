package com.udemy.matriculas.auth.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisUsuarioDTO {
    
    @NotBlank(message = "Este campo es obligatorio")
    private String username;
    
    @NotBlank(message = "Este campo es obligatorio")
    private String password;
    
}
