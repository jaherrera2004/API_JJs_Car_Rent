package com.ReservaVehiculos.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class VehiculoDto {

    private Integer id;
    private String placa;
    private Integer anio;
    private float kilometraje;
    private float valorDia;
    private String color;
    private boolean activo;
    private Integer idModelo;

}
