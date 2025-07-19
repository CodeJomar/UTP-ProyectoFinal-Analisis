package com.udemy.matriculas.registros.models.entities;

import com.udemy.matriculas.auth.models.entities.Usuario;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiantes")
public class Estudiante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    
    @Column(name = "nombres", nullable = false)
    private String nombre;
    
    @Column(name = "apellidos", nullable = false)
    private String apellido;
    
    @Column(name = "celulares", nullable = false)
    private String celular;
    
    @Column(name = "documento_identidad", nullable = false)
    private String dni;
    
    @Column(name = "estado_estudiante", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;
    
}
