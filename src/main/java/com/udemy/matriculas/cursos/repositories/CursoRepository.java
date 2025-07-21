package com.udemy.matriculas.cursos.repositories;

import com.udemy.matriculas.cursos.models.entities.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Cursos, Long> {
}
