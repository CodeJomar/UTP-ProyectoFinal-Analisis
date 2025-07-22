package com.udemy.matriculas.registros.repositories;

import com.udemy.matriculas.registros.models.entities.Estudiante;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByUsuario_Username(String username);
//    List<Estudiante> findByEstadoUsuario(EstadoUsuario estado);
}
