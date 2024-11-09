package com.JJsCarRent.services.TipoVehiculo;

import com.JJsCarRent.models.dto.TipoVehiculoDto;
import com.JJsCarRent.models.request.tipoVehiculo.TipoVehiculoRequest;

import java.util.List;

public interface TipoVehiculoIService {
    void agregarTipoVehiculo(TipoVehiculoRequest request);

    List<TipoVehiculoDto> obtenerListaTipoVehiculos();

    void desactivarTipoVehiculo(Integer id);
}
