package com.ReservaVehiculos.controllers.modelos;

import com.ReservaVehiculos.models.dto.ModeloDto;
import com.ReservaVehiculos.models.request.modelo.ModeloRequest;
import com.ReservaVehiculos.services.modelos.ModeloIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/modelos")
public class ModeloController {

    private final ModeloIService modeloIService;

    @PostMapping
    public void agregarModelo(@RequestBody ModeloRequest modeloRequest){
        modeloIService.agregarModelo(modeloRequest);
    }

    @GetMapping("/{id}")
    public ModeloDto obtenerModeloPorId(@PathVariable Integer id){
        return modeloIService.obtenerPorId(id);
    }

}
