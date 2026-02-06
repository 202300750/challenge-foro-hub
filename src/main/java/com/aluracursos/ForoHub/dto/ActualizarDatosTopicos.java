package com.aluracursos.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;

public record ActualizarDatosTopicos(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String autor,
        @NotBlank String curso
) {
}
