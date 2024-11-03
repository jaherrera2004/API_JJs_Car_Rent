package com.JJsCarRent.models.request.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class AuthRequest {

    @NotEmpty(message = "Debes enviar el username")
    private String usuario;

    @NotEmpty(message = "Debes enviar tu contraseña")
    private String contrasenia;
}
