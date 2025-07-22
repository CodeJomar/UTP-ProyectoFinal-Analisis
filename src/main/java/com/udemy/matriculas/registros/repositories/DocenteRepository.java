package com.udemy.matriculas.registros.repositories;

import com.udemy.matriculas.registros.models.entities.Docente;
import com.udemy.matriculas.registros.models.enums.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Optional<Docente> findByUsuario_Username(String username);
//    List<Docente> findByEstadoUsuario(EstadoUsuario estado);
}
