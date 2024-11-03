package com.JJsCarRent.models.dto;

import lombok.*;


@Getter
@Setter
@Builder
public class ModeloDto {

    private Integer id;
    private String modelo;
    private boolean activo;
    private Integer idMarca;
    private Integer idTipoVehiculo;

}
