package com.JJsCarRent.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
public class ReservaEntity {

    private Integer id;
    private Integer idVehiculo;
    private Integer idUsuario;
    private Integer idEstado;
    private LocalDate fechaInicio;
    private LocalDate fechaEntrega;


}
