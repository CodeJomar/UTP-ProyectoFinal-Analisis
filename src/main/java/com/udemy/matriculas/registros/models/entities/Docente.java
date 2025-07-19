package com.udemy.matriculas.registros.models.entities;

import com.udemy.matriculas.auth.models.entities.Usuario;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "docentes")
public class Docente {
    
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
    
    @Column(name = "especialidades", nullable = false)
    private String especialidad;
    
    @Column(name = "salarios", nullable = false)
    private BigDecimal salario;
    
    @Column(name = "estado_docente", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;
    
}
