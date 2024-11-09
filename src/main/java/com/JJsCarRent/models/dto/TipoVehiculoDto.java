package com.JJsCarRent.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class TipoVehiculoDto {

    private Integer id;
    private String tipo;
    private String descripcion;
    private boolean activo;

}
