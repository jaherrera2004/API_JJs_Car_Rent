package com.JJsCarRent.models.request.usuario;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    @NotNull(message = "La cédula no debe ser nula")
    @NotEmpty(message = "La cédula no debe estar vacía")
    @Pattern(regexp = "^[0-9]+$", message = "La cédula solo debe contener números")
    private String cedula;

    @NotNull(message = "El nombre no debe ser nulo")
    @NotEmpty(message = "El nombre no debe estar vacio")
    private String nombre;

    @NotNull(message = "El apellido no debe ser nulo")
    @NotEmpty(message = "El apellido no debe estar vacio")
    private String apellido;

    @NotNull(message = "El username no debe ser nulo")
    @NotEmpty(message = "El username no debe estar vacio")
    private String username;

    @Min(value = 18, message = "Debes ser mayor de edad")
    private Integer edad;

    @NotNull(message = "El email no debe ser nulo")
    @NotEmpty(message = "El email no debe estar vacio")
    private String email;

    @NotNull(message = "El telefono no debe ser nulo")
    @NotEmpty(message = "El telefono no debe estar vacio")
    private String telefono;

    @NotNull(message = "La contraseña no debe ser nulo")
    @NotEmpty(message = "La contraseña no debe estar vacio")
    @Size(min = 8, message = "La contraseña debe tener minimo 8 caracteres")
    private String contrasenia;

}
