package com.ReservaVehiculos.models.request.modelo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ModeloRequest {

    private String modelo;
    private Integer idMarca;
    private Integer idTipoVehiculo;

}
