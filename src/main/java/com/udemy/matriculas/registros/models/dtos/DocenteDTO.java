package com.udemy.matriculas.registros.models.dtos;

import com.udemy.matriculas.auth.models.entities.Rol;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocenteDTO {
    
    private String username;
    
    private String password;
    
    private String nombre;
    
    private String apellido;
    
    private String celular;
    
    private String dni;
    
    private String especialidad;
    
    private BigDecimal salario;
    
    private EstadoUsuario estado;
    
    private Rol rol;

}
