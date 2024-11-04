package com.JJsCarRent.services.usuarios;

import com.JJsCarRent.models.dto.UsuarioDto;
import com.JJsCarRent.models.request.usuario.UsuarioRequest;
import com.JJsCarRent.models.response.usuarios.UsuarioDatosResponse;

import java.util.List;

public interface UsuarioIService {
    void registrarUsuario(UsuarioRequest request);

    void desactivarUsuario(Integer id);

    void activarUsuario(Integer id);

    List<UsuarioDatosResponse> obtenerListaUsuarios();

    UsuarioDatosResponse obtenerUsuarioPorId(Integer id);

    UsuarioDto obtenerUsuarioPorUsername(String usernmae);
}