package com.udemy.matriculas.registros.models.dtos;

import com.udemy.matriculas.auth.models.entities.Rol;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import com.udemy.matriculas.validaciones.anotaciones.ClaveFormato;
import com.udemy.matriculas.validaciones.anotaciones.DocenteUnico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DocenteUnico
public class DocenteDTO {
    
    private Long id;
    
    @NotBlank(message = "Complete este campo")
    @Email(message = "Formato de correo inválido")
    private String username;
    
    @NotBlank(message = "Complete este campo")
    @ClaveFormato
    private String password;
    
    @NotBlank(message = "Complete este campo")
    private String nombre;
    
    @NotBlank(message = "Complete este campo")
    private String apellido;
    
    @NotBlank(message = "Complete este campo")
    private String celular;
    
    @NotBlank(message = "Complete este campo")
    private String dni;
    
    @NotBlank(message = "Complete este campo")
    private String especialidad;
    
    private BigDecimal salario;
    
    private EstadoUsuario estado;
    
    private Rol rol;

}
