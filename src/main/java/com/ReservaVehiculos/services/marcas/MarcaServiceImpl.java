package com.ReservaVehiculos.services.marcas;

import com.ReservaVehiculos.mappers.MarcaMapper;
import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.exceptions.HttpGenericException;
import com.ReservaVehiculos.models.request.marcas.MarcaRequest;
import com.ReservaVehiculos.repository.marcas.MarcaIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaIService{

   private final MarcaMapper marcaMapper;
   private final MarcaIRepository marcaIRepository;

    @Override
    public void agregarMarca(MarcaRequest request) {

        if(marcaIRepository.existsByMarca(request.getMarca())){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"Esta marca ya existe");
        }

        MarcaDto marcaDto= construirMarca(request);
        marcaIRepository.save(marcaMapper.toEntity(marcaDto));


    }

    @Override
    public List<MarcaDto> obtenerListaMarcas() {
        return marcaIRepository.findAll()
                .stream()
                .map(marcaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void desactivarMarca(Integer id) {
        if(!marcaIRepository.existsById(id)){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"El id de la marca que has ingresado no existe");
        }

        marcaIRepository.desactivarMarca(id);
    }

    @Override
    public void activarMarca(Integer id) {
        if(!marcaIRepository.existsById(id)){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"El id de la marca que has ingresado no existe");
        }
        marcaIRepository.activarMarca(id);
    }

    @Override
    public MarcaDto obtenerPorId(Integer id) {
        if(!marcaIRepository.existsById(id)){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"El id de la marca que has ingresado no existe");
        }
        return marcaMapper.toDto(marcaIRepository.findById(id));
    }

    private MarcaDto construirMarca(MarcaRequest request){
        return  MarcaDto.builder()
                .marca(request.getMarca())
                .activo(true)
                .build();
    }
}
