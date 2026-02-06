package com.aluracursos.ForoHub.repositorio;

import com.aluracursos.ForoHub.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
