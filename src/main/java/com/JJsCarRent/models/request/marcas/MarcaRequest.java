package com.JJsCarRent.models.request.marcas;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MarcaRequest {

    @NotNull(message = "La marca no debe ser nula")
    private String marca;

}
