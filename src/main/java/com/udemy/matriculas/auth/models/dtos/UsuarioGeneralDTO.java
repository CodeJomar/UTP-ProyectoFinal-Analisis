package com.udemy.matriculas.auth.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioGeneralDTO {
    private String usuarioId;
    private String nombreCompleto;
    private String email;
    private String tipo;
    private String telefono;
    private String estado;
}