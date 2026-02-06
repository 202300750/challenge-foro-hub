package com.aluracursos.ForoHub.dto;

import java.time.LocalDateTime;

public record ListadoDatosTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String autor,
        String curso
) {}
