package com.udemy.matriculas.eventos.repositories;

import com.udemy.matriculas.eventos.models.entities.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Eventos, Long> {
}