package com.ReservaVehiculos.controllers;

import com.ReservaVehiculos.models.request.usuario.UsuarioRequest;
import com.ReservaVehiculos.services.usuario.UsuarioIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioIService usuarioIService;

    @PostMapping
    public void registrarUsuario(@RequestBody UsuarioRequest request){
        usuarioIService.registrarUsuario(request);
    }
}
