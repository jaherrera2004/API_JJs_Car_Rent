package com.JJsCarRent.models.response.usuarios;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDatosResponse {

    private Integer id;
    private String cedula;
    private String email;
    private String username;
    private String nombre;
    private String apellido;
    private String telefono;
    private String rol;

}

