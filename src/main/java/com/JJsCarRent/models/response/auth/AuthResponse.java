package com.JJsCarRent.models.response.auth;

import com.JJsCarRent.models.response.usuarios.UsuarioDatosResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {

    private String accessToken;
    private UsuarioDatosResponse usuario;

}
