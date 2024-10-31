package com.ReservaVehiculos.models.request.marcas;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarcaLogoRequest {

    @NotNull(message = "La marca no debe ser nula")
    private String marca;
    @NotNull(message = "Debes enviar el logo")
    private MultipartFile logo;

}
