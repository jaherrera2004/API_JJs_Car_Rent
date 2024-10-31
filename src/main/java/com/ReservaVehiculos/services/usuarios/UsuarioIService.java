package com.ReservaVehiculos.services.usuarios;

import com.ReservaVehiculos.models.dto.UsuarioDto;
import com.ReservaVehiculos.models.request.usuario.UsuarioRequest;

import java.util.List;

public interface UsuarioIService {
    void registrarUsuario(UsuarioRequest request);

    void desactivarUsuario(Integer id);

    void activarUsuario(Integer id);

    List<UsuarioDto> obtenerListaUsuarios();

    UsuarioDto obtenerUsuarioPorId(Integer id);

    UsuarioDto obtenerUsuarioPorUsername(String usernmae);
}