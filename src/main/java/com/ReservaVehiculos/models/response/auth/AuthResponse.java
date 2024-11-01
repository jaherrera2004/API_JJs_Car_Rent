package com.ReservaVehiculos.models.response.auth;

import com.ReservaVehiculos.models.response.usuarios.UsuarioDatosResponse;
import lombok.AllArgsConstructor;
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
