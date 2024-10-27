package com.ReservaVehiculos.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ModeloEntity {

    private Integer id;
    private String modelo;
    private boolean activo;
    private Integer idMarca;
    private Integer idTipoVehiculo;

}
