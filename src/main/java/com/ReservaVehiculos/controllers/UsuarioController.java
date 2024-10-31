package com.ReservaVehiculos.controllers;

import com.ReservaVehiculos.models.dto.UsuarioDto;
import com.ReservaVehiculos.models.request.usuario.UsuarioRequest;
import com.ReservaVehiculos.services.usuarios.UsuarioIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioIService usuarioIService;

    @PostMapping
    public void registrarUsuario(@RequestBody @Valid  UsuarioRequest request){
        usuarioIService.registrarUsuario(request);
    }

    @DeleteMapping("/{id}")
    public void desactivarUsuario(@PathVariable Integer id){
        usuarioIService.desactivarUsuario(id);
    }

    @PutMapping("/{id}")
    public void activarUsuario(@PathVariable Integer id){
        usuarioIService.activarUsuario(id);
    }

    @GetMapping("/{id}")
    public UsuarioDto obtenerUsuarioPorId(@PathVariable Integer id){
        return usuarioIService.obtenerUsuarioPorId(id);
    }

    @GetMapping
    public List<UsuarioDto>obtenerListaUsuarios(){
        return usuarioIService.obtenerListaUsuarios();
    }
}
