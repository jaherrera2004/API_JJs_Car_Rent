package com.ReservaVehiculos.services.usuarios;

import com.ReservaVehiculos.models.request.usuario.UsuarioRequest;

public interface UsuarioIService {
    public void registrarUsuario(UsuarioRequest request);
    public void eliminarUsuario(Integer id);
}