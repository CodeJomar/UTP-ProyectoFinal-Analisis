package com.udemy.matriculas.auth.models.entities;

import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.models.entities.Estudiante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(name = "correo_udemy", unique = true, nullable = false)
    private String username;
    
    @Column(name = "contraseña", nullable = false)
    private String password;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Estudiante estudiante;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Docente docente;
    
    /* ======= Estos son para convertir mi Entidad (Usuario) a un UserDetails (de SpringBoot) ======= */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.getNombre().name()));
    }
    
    @Override public String getUsername() { return username; }
    @Override public String getPassword() { return password; }
    
}
