package com.ReservaVehiculos.models.request.marcas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MarcaLogoRequest {

    private String marca;
    private MultipartFile logo;

}
