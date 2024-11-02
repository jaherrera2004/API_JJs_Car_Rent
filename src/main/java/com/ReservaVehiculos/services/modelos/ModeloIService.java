package com.ReservaVehiculos.services.modelos;

import com.ReservaVehiculos.models.dto.ModeloDto;
import com.ReservaVehiculos.models.request.modelo.ModeloRequest;

import java.util.List;

public interface ModeloIService {
    void agregarModelo(ModeloRequest modeloRequest);

    ModeloDto obtenerPorId(Integer id);

    List<ModeloDto> obtenerListaModelos();
}
