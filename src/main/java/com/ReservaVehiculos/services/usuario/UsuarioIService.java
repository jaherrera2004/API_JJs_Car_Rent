package com.ReservaVehiculos.services.usuario;

import com.ReservaVehiculos.models.request.usuario.UsuarioRequest;

public interface UsuarioIService {
    public void registrarUsuario(UsuarioRequest request);
    public void eliminarUsuario(Integer id);
}