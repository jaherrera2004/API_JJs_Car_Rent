package com.ReservaVehiculos.services.marcas;

import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.request.marcas.MarcaLogoRequest;
import com.ReservaVehiculos.models.request.marcas.MarcaRequest;
import com.ReservaVehiculos.models.response.marcas.MarcaConLogoResponse;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.util.List;

public interface MarcaIService {
    void agregarMarca(MarcaRequest request);

    List<MarcaDto> obtenerListaMarcas();

    void desactivarMarca(Integer id);

    void activarMarca(Integer id);

    MarcaDto obtenerPorId(Integer id);

    void agregarLogo(MarcaLogoRequest request) throws IOException;

    Pair<ByteArrayResource, String> obtenerLogo(Integer id) throws IOException;

    void eliminarLogo(Integer id) throws IOException;

    List<MarcaConLogoResponse> obtenerMarcasConLogo();
}
