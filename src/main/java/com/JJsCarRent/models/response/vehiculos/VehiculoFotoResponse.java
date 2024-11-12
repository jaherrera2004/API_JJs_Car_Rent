package com.JJsCarRent.models.response.vehiculos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class VehiculoFotoResponse {

    private VehiculosDatosResponse VehiculoInfo;
    private String base64Image;
    private String mediaType;

}
