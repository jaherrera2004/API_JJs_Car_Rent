package com.ReservaVehiculos.services.marcas;

import com.ReservaVehiculos.mappers.MarcaMapper;
import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.exceptions.HttpGenericException;
import com.ReservaVehiculos.models.request.marcas.MarcaRequest;
import com.ReservaVehiculos.repository.marcas.MarcaIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaIService{

   private final MarcaMapper marcaMapper;
   private final MarcaIRepository marcaIRepository;

    @Override
    public MarcaDto agregarMarca(MarcaRequest request) {

        if(marcaIRepository.existsByMarca(request.getMarca())){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"Esta marca ya existe");
        }

        MarcaDto marcaDto= construirMarca(request);
        marcaIRepository.save(marcaMapper.toEntity(marcaDto));

        return marcaDto;
    }

    private MarcaDto construirMarca(MarcaRequest request){
        return  MarcaDto.builder()
                .marca(request.getMarca())
                .activo(true)
                .build();
    }
}
