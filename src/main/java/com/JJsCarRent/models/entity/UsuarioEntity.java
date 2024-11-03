package com.JJsCarRent.models.entity;


import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UsuarioEntity {
    
    private Integer id;
    private String cedula;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String username;
    private String email;
    private String telefono;
    private String contrasenia;
    private boolean activo;
    private String foto;
    private Integer idRol;

}
