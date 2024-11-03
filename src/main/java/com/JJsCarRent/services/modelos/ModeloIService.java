package com.JJsCarRent.services.modelos;

import com.JJsCarRent.models.dto.ModeloDto;
import com.JJsCarRent.models.request.modelo.ModeloRequest;

import java.util.List;

public interface ModeloIService {
    void agregarModelo(ModeloRequest modeloRequest);

    ModeloDto obtenerPorId(Integer id);

    List<ModeloDto> obtenerListaModelos();
}
