package com.JJsCarRent.models.response.reservas;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ReservaResponse {

    private Integer id;
    private String placaVehiculo;
    private String cedula;
    private String estado;
    private LocalDate fechaInicio;
    private LocalDate fechaEntrega;

}
