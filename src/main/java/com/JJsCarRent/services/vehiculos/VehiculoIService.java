package com.JJsCarRent.services.vehiculos;

import com.JJsCarRent.models.request.vehiculo.VehiculoRequest;
import com.JJsCarRent.models.response.vehiculos.VehiculoFotoResponse;
import com.JJsCarRent.models.response.vehiculos.VehiculosDatosResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface VehiculoIService {
    void agregarVehiculo(VehiculoRequest request, MultipartFile foto) throws IOException;

    List<VehiculosDatosResponse> obtenerListaVehiculos();

    void desactivarVehiculo(Integer id);

    void activarVehiculo(Integer id);

    List<VehiculoFotoResponse> obtenerVehiculosConFotos();

    List<VehiculoFotoResponse> obtenerVehiculosDisponibles(Integer id, LocalDate fechaInicio, LocalDate fechaEntrega);
}
