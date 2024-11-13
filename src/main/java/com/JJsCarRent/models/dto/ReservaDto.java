package com.JJsCarRent.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ReservaDto {

    private Integer id;
    private Integer idVehiculo;
    private Integer idUsuario;
    private Integer idEstado;
    private LocalDate fechaInicio;
    private Local fechaEntrega;
}
