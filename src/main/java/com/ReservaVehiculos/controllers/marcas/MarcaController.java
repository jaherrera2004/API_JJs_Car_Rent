package com.ReservaVehiculos.controllers.marcas;

import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.request.marcas.MarcaLogoRequest;
import com.ReservaVehiculos.models.request.marcas.MarcaRequest;
import com.ReservaVehiculos.models.response.marcas.MarcaConLogoResponse;
import com.ReservaVehiculos.services.marcas.MarcaIService;
import com.ReservaVehiculos.utils.ArchivoUtil;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/marcas")
public class MarcaController {

    private final MarcaIService marcaIService;
    private final ArchivoUtil archivoUtil;

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
    public void agregarLogo(@ModelAttribute MarcaLogoRequest request) throws IOException {
        marcaIService.agregarLogo(request);
    }

    @GetMapping("/logo/{id}")
    public ResponseEntity<ByteArrayResource> obtenerLogo(@PathVariable Integer id) throws IOException {
        Pair<ByteArrayResource,String> data = marcaIService.obtenerLogo(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(data.b))
                .body(data.a);
    }

    @DeleteMapping("/logo/{id}")
    public void eliminarLogo(@PathVariable Integer id) throws IOException {
        marcaIService.eliminarLogo(id);
    }

    @GetMapping("/logos")
    public List<MarcaConLogoResponse> obtenerMarcasConLogo(){
        return marcaIService.obtenerMarcasConLogo();
    }
}
