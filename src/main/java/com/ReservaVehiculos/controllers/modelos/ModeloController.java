package com.ReservaVehiculos.controllers.modelos;

import com.ReservaVehiculos.models.request.modelo.ModeloRequest;
import com.ReservaVehiculos.services.modelos.ModeloIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/modelos")
public class ModeloController {

    private final ModeloIService modeloIService;

    @PostMapping
    public void agregarModelo(@RequestBody ModeloRequest modeloRequest){
        modeloIService.agregarModelo(modeloRequest);
    }

}
