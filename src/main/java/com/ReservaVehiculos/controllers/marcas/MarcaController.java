package com.ReservaVehiculos.controllers.marcas;

import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.request.marcas.MarcaLogoRequest;
import com.ReservaVehiculos.models.request.marcas.MarcaRequest;
import com.ReservaVehiculos.services.marcas.MarcaIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/marcas")
public class MarcaController {

    private final MarcaIService marcaIService;

    @PostMapping
    public void agregarMarca(@RequestBody MarcaRequest request) {
        marcaIService.agregarMarca(request);
    }

    @GetMapping
    public List<MarcaDto> obtenerListaMarcas() {
        return marcaIService.obtenerListaMarcas();
    }

    @DeleteMapping("/{id}")
    public void desactivarMarca(@PathVariable Integer id){
        marcaIService.desactivarMarca(id);
    }

    @PutMapping("/{id}")
    public void activarMarca(@PathVariable Integer id){
        marcaIService.activarMarca(id);
    }

    @GetMapping("/{id}")
    public MarcaDto obtenerMarcaPorId(@PathVariable Integer id){
        return marcaIService.obtenerPorId(id);
    }

    @PostMapping("/logo")
    public void agregarFoto(@ModelAttribute  MarcaLogoRequest request) throws IOException {
        marcaIService.agregarLogo(request);
    }

}
