package com.JJsCarRent.models.request.tipoVehiculo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoVehiculoRequest {

    @NotEmpty(message = "El tipo de vehiculo no debe estar vacio")
    @NotNull(message = "Debes enviar el tipo de vehiculo")
    private String tipo;

    @NotEmpty(message = "La descripcion no debe estar vacia")
    @NotNull(message = "Debes enviar la descripcion")
    private String descripcion;

}
