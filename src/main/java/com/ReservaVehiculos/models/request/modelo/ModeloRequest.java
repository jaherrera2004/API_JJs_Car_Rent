package com.ReservaVehiculos.models.request.modelo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ModeloRequest {

    @NotNull(message = "El modelo no debe ser nulo")
    @NotEmpty(message = "El modelo no debe estar vacio")
    private String modelo;

    @NotNull(message = "Debes enviar una marca")
    private Integer idMarca;

    @NotNull(message = "Deber enviar el tipo de vehiculo")
    private Integer idTipoVehiculo;

}
