package com.ReservaVehiculos.models.request.vehiculo;

import jakarta.validation.constraints.*;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class VehiculoRequest {

    @NotNull(message = "La placa no puede ser nula")
    @Pattern(regexp = "^[A-Z]{3}-\\d{3}$", message = "La placa debe tener el formato AAA-111")
    private String placa;

    @NotNull(message = "El año no puede ser nulo")
    @Min(value = 1900, message = "El año debe ser como mínimo 1900")
    @Max(value = 2024, message = "El año no puede ser mayor que 2024")
    private Integer anio;

    @NotNull(message = "El kilometraje no puede ser nulo")
    @PositiveOrZero(message = "El kilometraje debe ser positivo o 0")
    private float kilometraje;

    @NotNull(message = "El valor por día no puede ser nulo")
    @Positive(message = "El valor por día debe ser positivo")
    private float valorDia;

    @NotNull(message = "El color no puede ser nulo")
    private String color;

    @NotNull(message = "El ID del modelo no puede ser nulo")
    private Integer idModelo;
}
