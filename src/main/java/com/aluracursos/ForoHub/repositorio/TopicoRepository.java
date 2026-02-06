package com.aluracursos.ForoHub.repositorio;

import com.aluracursos.ForoHub.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findByCursoNombreAndFechaCreacionYear(
            String nombreCurso,
            int anio,
            Pageable pageable
    );

    @Query("""
        SELECT t FROM Topico t
        WHERE t.curso.nombre = :curso
        AND YEAR(t.fechaCreacion) = :anio
        """)
    Page<Topico> buscarPorCursoYAnio(
            @Param("curso") String curso,
            @Param("anio") int anio,
            Pageable pageable
    );

}
