package com.JJsCarRent.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class MarcaDto {

    private int id;
    private String marca;
    private boolean activo;

}
