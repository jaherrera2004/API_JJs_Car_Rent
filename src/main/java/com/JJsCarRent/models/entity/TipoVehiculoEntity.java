package com.JJsCarRent.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TipoVehiculoEntity {

    private Integer id;
    private String tipo;
    private String descripcion;
    private boolean activo;
}
