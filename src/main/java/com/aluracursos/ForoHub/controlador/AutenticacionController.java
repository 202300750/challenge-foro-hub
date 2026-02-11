package com.aluracursos.ForoHub.controlador;

import com.aluracursos.ForoHub.dto.AutenticacionDatosUsuario;
import com.aluracursos.ForoHub.servicio.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid AutenticacionDatosUsuario datos) {
        var token = new UsernamePasswordAuthenticationToken(
                datos.email(),
                datos.password()
        );

        var aunthenticacion = authenticationManager.authenticate(token);

        var jwt = tokenService.generarToken(aunthenticacion.getName());

        return ResponseEntity.ok(jwt);
    }
}
