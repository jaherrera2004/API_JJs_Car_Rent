package com.JJsCarRent.services.TipoVehiculo;

import com.JJsCarRent.models.dto.TipoVehiculoDto;
import com.JJsCarRent.models.exceptions.HttpGenericException;
import com.JJsCarRent.models.request.tipoVehiculo.TipoVehiculoRequest;
import com.JJsCarRent.repository.tipoVehiculos.TipoVehiculoIRepository;
import com.JJsCarRent.utils.mappers.TipoVehiculoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TipoVehiculoServiceImpl implements TipoVehiculoIService {

    private final TipoVehiculoIRepository tipoVehiculoIRepository;
    private final TipoVehiculoMapper tipoVehiculoMapper;

    @Override
    public void agregarTipoVehiculo(TipoVehiculoRequest request) {

        if (tipoVehiculoIRepository.existsByTipo(request.getTipo())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Este tipo de vehiculo ya existe");
        }

        TipoVehiculoDto dto = construirTipoVehiculoDto(request);

        tipoVehiculoIRepository.save(tipoVehiculoMapper.toEntity(dto));
    }

    @Override
    public List<TipoVehiculoDto> obtenerListaTipoVehiculos() {
        return tipoVehiculoIRepository.findAll().stream()
                .map(tipoVehiculoMapper::toDto)
                .toList();
    }

    @Override
    public void desactivarTipoVehiculo(Integer id) {

        if (!tipoVehiculoIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El tipo de vehiculo que has ingresado no existe");
        }

        tipoVehiculoIRepository.desactivar(id);
    }

    @Override
    public void activarTipoVehiculo(Integer id) {
        if (!tipoVehiculoIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El tipo de vehiculo que has ingresado no existe");
        }

        tipoVehiculoIRepository.activar(id);
    }

    private TipoVehiculoDto construirTipoVehiculoDto(TipoVehiculoRequest request) {
        return TipoVehiculoDto.builder()
                .activo(true)
                .tipo(request.getTipo())
                .descripcion(request.getDescripcion())
                .build();
    }
}
