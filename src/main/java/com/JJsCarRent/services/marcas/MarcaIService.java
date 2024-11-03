package com.JJsCarRent.services.marcas;

import com.JJsCarRent.models.dto.MarcaDto;
import com.JJsCarRent.models.request.marcas.MarcaLogoRequest;
import com.JJsCarRent.models.request.marcas.MarcaRequest;
import com.JJsCarRent.models.response.marcas.MarcaConLogoResponse;
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
