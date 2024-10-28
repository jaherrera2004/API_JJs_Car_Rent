package com.ReservaVehiculos.controllers.marcas;

import com.ReservaVehiculos.models.request.marcas.MarcaLogoRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/marcas-logo")
public class MarcaLogoController {

    @PostMapping
    public void agregarFoto(MarcaLogoRequest request){

    }
}
