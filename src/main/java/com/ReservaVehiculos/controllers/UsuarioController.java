package com.ReservaVehiculos.controllers;

import com.ReservaVehiculos.models.dto.UsuarioDto;
import com.ReservaVehiculos.models.request.usuario.UsuarioRequest;
import com.ReservaVehiculos.services.usuarios.UsuarioIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('usuario:desactivar-usuario')")
    @DeleteMapping("/{id}")
    public void desactivarUsuario(@PathVariable Integer id){
        usuarioIService.desactivarUsuario(id);
    }

    @PreAuthorize("hasAuthority('usuario:activar-usuario')")
    @PutMapping("/{id}")
    public void activarUsuario(@PathVariable Integer id){
        usuarioIService.activarUsuario(id);
    }

    @PreAuthorize("hasAuthority('usuario:obtener-usuario')")
    @GetMapping("/{id}")
    public UsuarioDto obtenerUsuarioPorId(@PathVariable Integer id){
        return usuarioIService.obtenerUsuarioPorId(id);
    }

    @PreAuthorize("hasAuthority('usuario:obtener-lista-usuarios')")
    @GetMapping
    public List<UsuarioDto>obtenerListaUsuarios(){
        return usuarioIService.obtenerListaUsuarios();
    }
}
