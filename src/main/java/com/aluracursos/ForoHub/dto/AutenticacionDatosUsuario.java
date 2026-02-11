package com.aluracursos.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticacionDatosUsuario(

        @NotBlank
        String email,

        @NotBlank
        String password

) {}
