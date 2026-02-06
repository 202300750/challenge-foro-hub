package com.aluracursos.ForoHub.controlador;

import com.aluracursos.ForoHub.dto.ActualizarDatosTopicos;
import com.aluracursos.ForoHub.dto.DetalleDatosTopicos;
import com.aluracursos.ForoHub.dto.ListadoDatosTopicos;
import com.aluracursos.ForoHub.dto.RegistroDatosTopicos;
import com.aluracursos.ForoHub.modelo.Curso;
import com.aluracursos.ForoHub.modelo.Topico;
import com.aluracursos.ForoHub.modelo.Usuario;
import com.aluracursos.ForoHub.repositorio.CursoRepository;
import com.aluracursos.ForoHub.repositorio.TopicoRepository;
import com.aluracursos.ForoHub.repositorio.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrar(@RequestBody @Valid RegistroDatosTopicos datos) {
        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity
                    .badRequest()
                    .body("Ya existe un topico con el mismo titulo y mensaje.");
        }
        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        var topico = new Topico(datos.titulo(), datos.mensaje(), autor, curso);
        repository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<ListadoDatosTopicos>> listar(
            @PageableDefault(
                    size = 10,
                    sort = "fechaCreacion",
                    direction = Sort.Direction.ASC
            ) Pageable pageable,

            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio
    ) {

        Page<Topico> page;

        if (curso != null && anio != null) {
            page = repository.buscarPorCursoYAnio(curso, anio, pageable);
        } else {
            page = repository.findAll(pageable);
        }

        var respuesta = page.map(t -> new ListadoDatosTopicos(
                t.getId(),
                t.getTitulo(),
                t.getMensaje(),
                t.getFechaCreacion(),
                t.getStatus().name(),
                t.getAutor().getNombre(),
                t.getCurso().getNombre()
        ));

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleDatosTopicos> detalle(@PathVariable Long id) {

        var topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        var dto = new DetalleDatosTopicos(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus().name(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid ActualizarDatosTopicos datos
            ) {
        var optionalTopico = repository.findById(id);

        if (!optionalTopico.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        var topico = optionalTopico.get();

        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest()
                    .body("Ya existe un topico con ese titulo y mensaje");
        }
        topico.actualizarDatosTopicos(datos);

        return ResponseEntity.ok(
                new ListadoDatosTopicos(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus().name(),
                        topico.getAutor().getNombre(),
                        topico.getCurso().getNombre()
                )
        );
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        var optionalTopico = repository.findById(id);

        if (!optionalTopico.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
