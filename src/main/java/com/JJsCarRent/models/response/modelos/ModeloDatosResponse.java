package com.JJsCarRent.models.response.modelos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ModeloDatosResponse {

    private Integer id;
    private String modelo;
    private boolean activo;
    private String marca;
    private String tipoVehiculo;

}
