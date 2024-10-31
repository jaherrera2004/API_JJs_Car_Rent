package com.ReservaVehiculos.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class VehiculoEntity {

    private Integer id;
    private String placa;
    private Integer anio;
    private float kilometraje;
    private float valorDia;
    private String color;
    private boolean activo;
    private Integer idModelo;

}
