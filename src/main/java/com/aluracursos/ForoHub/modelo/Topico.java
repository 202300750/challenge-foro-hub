package com.aluracursos.ForoHub.modelo;

import com.aluracursos.ForoHub.dto.ActualizarDatosTopicos;
import com.aluracursos.ForoHub.dto.RegistroDatosTopicos;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "topicos")
public class Topico {
    // Getter and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico() {
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopico.ABIERTO;
    }

    public Topico(String titulo, String mensaje, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopico.ABIERTO;
    }
    public void actualizarDatosTopicos(ActualizarDatosTopicos datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
    }
}
