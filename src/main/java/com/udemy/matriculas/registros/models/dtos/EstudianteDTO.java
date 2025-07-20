package com.udemy.matriculas.registros.models.dtos;

import com.udemy.matriculas.auth.models.entities.Rol;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteDTO {
    
    private Long id;
    
    private String username;
    
    private String password;
    
    private String nombre;
    
    private String apellido;
    
    private String celular;
    
    private String dni;
    
    private EstadoUsuario estado;
    
    private Rol rol;
}
