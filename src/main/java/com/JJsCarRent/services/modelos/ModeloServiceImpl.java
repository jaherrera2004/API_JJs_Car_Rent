package com.JJsCarRent.services.modelos;

import com.JJsCarRent.utils.mappers.ModeloMapper;
import com.JJsCarRent.models.dto.ModeloDto;
import com.JJsCarRent.models.exceptions.HttpGenericException;
import com.JJsCarRent.models.request.modelo.ModeloRequest;
import com.JJsCarRent.repository.marcas.MarcaIRepository;
import com.JJsCarRent.repository.modelos.ModeloIRepository;
import com.JJsCarRent.repository.tipoVehiculos.TipoVehiculoIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModeloServiceImpl implements ModeloIService {

    private final ModeloIRepository modeloIRepository;
    private final ModeloMapper modeloMapper;
    private final MarcaIRepository marcaIRepository;
    private final TipoVehiculoIRepository tipoVehiculoIRepository;

    @Override
    public void agregarModelo(ModeloRequest request) {

        if (!marcaIRepository.existsById(request.getIdMarca())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "No hemos encontrado la marca del modelo");
        }

        if (modeloIRepository.existsByModelo(request.getModelo())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Ya existe este modelo");
        }

        if (!tipoVehiculoIRepository.existsById(request.getIdTipoVehiculo())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "No hemos encontrado el tipo de vehiculo del modelo");
        }

        ModeloDto modeloDto = construirModelo(request);

        modeloIRepository.save(modeloMapper.toEntity(modeloDto));

    }

    @Override
    public ModeloDto obtenerPorId(Integer id) {

        if (!modeloIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "No hemos encontrado ese modelo");
        }

        return modeloMapper.toDto(modeloIRepository.findById(id));
    }

    @Override
    public List<ModeloDto> obtenerListaModelos() {
        return modeloIRepository.findAll().stream()
                .map(modeloMapper::toDto)
                .toList();
    }

    private ModeloDto construirModelo(ModeloRequest request) {
        return ModeloDto.builder()
                .modelo(request.getModelo())
                .idMarca(request.getIdMarca())
                .idTipoVehiculo(request.getIdTipoVehiculo())
                .activo(true)
                .build();
    }
}
