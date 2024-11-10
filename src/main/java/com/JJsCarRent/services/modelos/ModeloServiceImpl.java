package com.JJsCarRent.services.modelos;

import com.JJsCarRent.models.response.modelos.ModeloDatosResponse;
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
import org.springframework.ui.Model;

import java.util.ArrayList;
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
    public List<ModeloDatosResponse> obtenerListaModelos() {

        List<ModeloDto> listaModeloDto = modeloIRepository.findAll().stream()
                .map(modeloMapper::toDto)
                .toList();

        List<ModeloDatosResponse> listaModeloDatosResponses = new ArrayList<>();
        listaModeloDto.forEach(modeloDto -> listaModeloDatosResponses.add(construirModeloResponse(modeloDto)));

        return listaModeloDatosResponses;
    }

    @Override
    public void desactivarModelo(Integer id) {
        if (!modeloIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El modelo ingresado no existe");
        }

        modeloIRepository.desactivar(id);

    }

    @Override
    public void activarModelo(Integer id) {

        if(!modeloIRepository.existsById(id)){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"El modelo has ingresado no existe");
        }

        modeloIRepository.activar(id);
    }

    private ModeloDto construirModelo(ModeloRequest request) {
        return ModeloDto.builder()
                .modelo(request.getModelo())
                .idMarca(request.getIdMarca())
                .idTipoVehiculo(request.getIdTipoVehiculo())
                .activo(true)
                .build();
    }

    private ModeloDatosResponse construirModeloResponse(ModeloDto modeloDto) {
        return ModeloDatosResponse.builder()
                .id(modeloDto.getId())
                .modelo(modeloDto.getModelo())
                .marca(marcaIRepository.findMarcaById(modeloDto.getIdMarca()))
                .tipoVehiculo(tipoVehiculoIRepository.findTipoById(modeloDto.getIdTipoVehiculo()))
                .activo(modeloDto.isActivo())
                .build();
    }
}
