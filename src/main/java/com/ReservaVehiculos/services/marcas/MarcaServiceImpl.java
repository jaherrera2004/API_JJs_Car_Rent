package com.ReservaVehiculos.services.marcas;

import com.ReservaVehiculos.mappers.MarcaMapper;
import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.exceptions.HttpGenericException;
import com.ReservaVehiculos.models.request.marcas.MarcaLogoRequest;
import com.ReservaVehiculos.models.request.marcas.MarcaRequest;
import com.ReservaVehiculos.repository.marcas.MarcaIRepository;
import com.ReservaVehiculos.utils.ArchivoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaIService {

    private final MarcaMapper marcaMapper;
    private final MarcaIRepository marcaIRepository;
    private final ArchivoUtil archivoUtil;

    @Override
    public void agregarMarca(MarcaRequest request) {

        if (marcaIRepository.existsByMarca(request.getMarca())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Esta marca ya existe");
        }

        MarcaDto marcaDto = construirMarca(request);
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
        if (!marcaIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El id de la marca que has ingresado no existe");
        }

        marcaIRepository.desactivarMarca(id);
    }

    @Override
    public void activarMarca(Integer id) {
        if (!marcaIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El id de la marca que has ingresado no existe");
        }
        marcaIRepository.activarMarca(id);
    }

    @Override
    public MarcaDto obtenerPorId(Integer id) {
        if (!marcaIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El id de la marca que has ingresado no existe");
        }
        return marcaMapper.toDto(marcaIRepository.findById(id));
    }

    @Override
    public void agregarLogo(MarcaLogoRequest request) throws IOException {

        if (!marcaIRepository.existsByMarca(request.getMarca())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "La marca que has ingresado no existe");
        }

        if(!archivoUtil.esExtensionValida(request.getLogo())){
            throw  new HttpGenericException(HttpStatus.BAD_REQUEST,"Debes enviar archivos en formato jpg, png o jepg");
        }

        if (!archivoUtil.esTamanioValido(request.getLogo())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "la foto de demasiado grande");
        }

        String nombreFoto = archivoUtil.subirArchivo(request.getLogo());
        marcaIRepository.actualizarFoto(nombreFoto, request.getMarca());
    }

    private MarcaDto construirMarca(MarcaRequest request) {
        return MarcaDto.builder()
                .marca(request.getMarca())
                .activo(true)
                .build();
    }
}
