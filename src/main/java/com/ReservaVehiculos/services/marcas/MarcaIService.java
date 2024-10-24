package com.ReservaVehiculos.services.marcas;

import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.request.marcas.MarcaRequest;

import java.util.List;

public interface MarcaIService {
    void agregarMarca(MarcaRequest request);
    List<MarcaDto> obtenerListaMarcas();
    void desactivarMarca(Integer id);
    void activarMarca(Integer id);
    MarcaDto obtenerPorId(Integer id);
}
