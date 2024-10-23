package com.ReservaVehiculos.controllers.marcas;

import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.request.marcas.MarcaRequest;
import com.ReservaVehiculos.services.marcas.MarcaIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/marcas")
public class MarcaController {

    private final MarcaIService marcaIService;

    @PostMapping
    public MarcaDto agregarMarca(@RequestBody  MarcaRequest request){
        return marcaIService.agregarMarca(request);
    }
}
