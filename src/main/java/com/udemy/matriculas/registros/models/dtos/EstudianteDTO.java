package com.udemy.matriculas.registros.models.dtos;

import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import com.udemy.matriculas.validaciones.anotaciones.ClaveFormato;
import com.udemy.matriculas.validaciones.anotaciones.EstudianteUnico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EstudianteUnico
public class EstudianteDTO {

    private Long id;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo es inválido")
    private String username;

    private String password;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El celular es obligatorio")
    @Size(min = 9, max = 15, message = "El celular debe tener entre 9 y 15 dígitos")
    private String celular;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    private String dni;

    private EstadoUsuario estado;
}
