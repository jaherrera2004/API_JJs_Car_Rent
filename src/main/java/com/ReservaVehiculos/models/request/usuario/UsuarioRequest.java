package com.ReservaVehiculos.models.request.usuario;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioRequest {

    private String cedula;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String email;
    private String telefono;
    private String contrasenia;

}
