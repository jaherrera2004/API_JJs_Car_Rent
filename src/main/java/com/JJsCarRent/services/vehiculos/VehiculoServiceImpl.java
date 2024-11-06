package com.JJsCarRent.services.vehiculos;

import com.JJsCarRent.models.dto.VehiculoDto;
import com.JJsCarRent.models.exceptions.HttpGenericException;
import com.JJsCarRent.models.request.vehiculo.VehiculoRequest;
import com.JJsCarRent.models.response.vehiculos.VehiculosDatosResponse;
import com.JJsCarRent.repository.modelos.ModeloIRepository;
import com.JJsCarRent.repository.vehiculos.VehiculoIRepository;
import com.JJsCarRent.utils.mappers.VehiculoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VehiculoServiceImpl implements VehiculoIService {

    private final VehiculoIRepository vehiculoIRepository;
    private final ModeloIRepository modeloIRepository;
    private final VehiculoMapper vehiculoMapper;

    @Override
    public void agregarVehiculo(VehiculoRequest request) {

        if (vehiculoIRepository.existsByPlaca(request.getPlaca())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Ya tenemos esta placa registrada");
        }

        if (!modeloIRepository.existsById(request.getIdModelo())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El modelo que has ingresado no esta registrado");
        }

        VehiculoDto vehiculoDto = construirVehiculo(request);
        vehiculoIRepository.save(vehiculoMapper.toEntity(vehiculoDto));
    }

    @Override
    public List<VehiculosDatosResponse> obtenerListaVehiculos() {

        List<VehiculoDto> listaVehiculosDto = vehiculoIRepository.findAll().stream()
                .map(vehiculoMapper::toDto)
                .toList();

        List<VehiculosDatosResponse> listaVehiculosResponse=new ArrayList<>();

        listaVehiculosDto.forEach(vehiculoDto -> listaVehiculosResponse.add(construirVehiculoResponse(vehiculoDto)));

        return listaVehiculosResponse;
    }

    private VehiculoDto construirVehiculo(VehiculoRequest request) {
        return VehiculoDto.builder()
                .placa(request.getPlaca())
                .color(request.getColor())
                .idModelo(request.getIdModelo())
                .kilometraje(request.getKilometraje())
                .valorDia(request.getValorDia())
                .anio(request.getAnio())
                .activo(true)
                .build();
    }

    private VehiculosDatosResponse construirVehiculoResponse(VehiculoDto vehiculoDto){
        return VehiculosDatosResponse.builder()
                .id(vehiculoDto.getId())
                .placa(vehiculoDto.getPlaca())
                .modelo(modeloIRepository.findModeloById(vehiculoDto.getIdModelo()))
                .anio(vehiculoDto.getAnio())
                .kilometraje(vehiculoDto.getKilometraje())
                .color(vehiculoDto.getColor())
                .activo(vehiculoDto.isActivo())
                .valorDia(vehiculoDto.getValorDia())
                .build();
    }
}
