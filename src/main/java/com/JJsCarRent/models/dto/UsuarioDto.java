package com.JJsCarRent.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class UsuarioDto {

    private Integer id;

    private String cedula;
    private String nombre;
    private String apellido;
    private String username;
    private Integer edad;
    private String email;
    private String telefono;
    private String contrasenia;
    private boolean activo;

    private Integer idRol;

}
