package com.aluracursos.ForoHub.repositorio;

import com.aluracursos.ForoHub.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
