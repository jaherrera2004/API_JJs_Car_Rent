package com.JJsCarRent.models.request.reserva;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter @Setter
public class ReservaRequest {

    @NotNull
    private Integer idUsuario;

    @NotNull
    private Integer idVehiculo;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private Local fechaEntrega;
}
