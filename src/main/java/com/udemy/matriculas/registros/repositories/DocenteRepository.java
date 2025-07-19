package com.udemy.matriculas.registros.repositories;

import com.udemy.matriculas.registros.models.entities.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Optional<Docente> findByUsuario_Username(String username);
}
