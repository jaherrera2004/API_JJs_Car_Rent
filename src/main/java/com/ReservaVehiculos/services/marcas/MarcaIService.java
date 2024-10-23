package com.ReservaVehiculos.services.marcas;

import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.request.marcas.MarcaRequest;

public interface MarcaIService {
    MarcaDto agregarMarca(MarcaRequest request);
}
