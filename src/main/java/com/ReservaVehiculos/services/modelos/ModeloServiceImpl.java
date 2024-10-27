package com.ReservaVehiculos.services.modelos;

import com.ReservaVehiculos.mappers.ModeloMapper;
import com.ReservaVehiculos.models.dto.ModeloDto;
import com.ReservaVehiculos.models.exceptions.HttpGenericException;
import com.ReservaVehiculos.models.request.modelo.ModeloRequest;
import com.ReservaVehiculos.repository.marcas.MarcaIRepository;
import com.ReservaVehiculos.repository.modelos.ModeloIRepository;
import com.ReservaVehiculos.repository.vehiculos.TipoVehiculoIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ModeloServiceImpl implements ModeloIService{

    private final ModeloIRepository modeloIRepository;
    private final ModeloMapper modeloMapper;
    private final MarcaIRepository marcaIRepository;
    private  final TipoVehiculoIRepository tipoVehiculoIRepository;

    @Override
    public void agregarModelo(ModeloRequest request) {

        if(!marcaIRepository.existsById(request.getIdMarca())){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"No hemos encontrado la marca del modelo");
        }

        if(modeloIRepository.existsByModelo(request.getModelo())){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"Ya existe este modelo");
        }

        if(!tipoVehiculoIRepository.existsById(request.getIdTipoVehiculo())){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"No hemos encontrado el tipo de vehiculo del modelo");
        }

        ModeloDto modeloDto = construirModelo(request);

        modeloIRepository.save(modeloMapper.toEntity(modeloDto));

    }

    private ModeloDto construirModelo(ModeloRequest request){
        return ModeloDto.builder()
                .modelo(request.getModelo())
                .idMarca(request.getIdMarca())
                .idTipoVehiculo(request.getIdTipoVehiculo())
                .activo(true)
                .build();
    }
}
