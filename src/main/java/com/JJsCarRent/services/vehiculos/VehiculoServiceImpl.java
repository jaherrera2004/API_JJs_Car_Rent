package com.JJsCarRent.services.vehiculos;

import com.JJsCarRent.models.dto.VehiculoDto;
import com.JJsCarRent.models.exceptions.HttpGenericException;
import com.JJsCarRent.models.request.vehiculo.VehiculoRequest;
import com.JJsCarRent.models.response.vehiculos.VehiculoFotoResponse;
import com.JJsCarRent.models.response.vehiculos.VehiculosDatosResponse;
import com.JJsCarRent.repository.marcas.MarcaIRepository;
import com.JJsCarRent.repository.modelos.ModeloIRepository;
import com.JJsCarRent.repository.vehiculos.VehiculoIRepository;
import com.JJsCarRent.utils.ArchivoUtil;
import com.JJsCarRent.utils.adapters.ImageAdapter;
import com.JJsCarRent.utils.mappers.VehiculoMapper;
import com.JJsCarRent.utils.strategy.TipoArchivo;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VehiculoServiceImpl implements VehiculoIService {

    private final VehiculoIRepository vehiculoIRepository;
    private final MarcaIRepository marcaIRepository;
    private final ModeloIRepository modeloIRepository;
    private final VehiculoMapper vehiculoMapper;
    private final ArchivoUtil archivoUtil;
    private final ImageAdapter imageAdapter;

    @Override
    public void agregarVehiculo(VehiculoRequest request, MultipartFile foto) throws IOException {

        if (vehiculoIRepository.existsByPlaca(request.getPlaca())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Ya tenemos esta placa registrada");
        }

        if (!modeloIRepository.existsById(request.getIdModelo())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El modelo que has ingresado no esta registrado");
        }

        if (!archivoUtil.esExtensionValida(foto.getOriginalFilename(), TipoArchivo.IMAGEN)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Has enviado la foto con un formato incorrecto");
        }

        if (!archivoUtil.esTamanioValido(foto.getBytes())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Has enviado una foto con un tamaño muy grande");
        }

        Pair<byte[], String> fotoWebp = archivoUtil.transformarAWebp(foto.getBytes(), foto.getOriginalFilename());

        VehiculoDto vehiculoDto = construirVehiculo(request);
        vehiculoIRepository.save(vehiculoMapper.toEntity(vehiculoDto));

        String nombreArchivo = archivoUtil.cambiarNombreArchivo(fotoWebp.b);

        archivoUtil.subirArchivo(fotoWebp.a, nombreArchivo);
        vehiculoIRepository.agregarFoto(nombreArchivo, vehiculoDto.getPlaca());
    }

    @Override
    public List<VehiculosDatosResponse> obtenerListaVehiculos() {

        List<VehiculoDto> listaVehiculosDto = vehiculoIRepository.findAll().stream()
                .map(vehiculoMapper::toDto)
                .toList();

        List<VehiculosDatosResponse> listaVehiculosResponse = new ArrayList<>();

        listaVehiculosDto.forEach(vehiculoDto -> listaVehiculosResponse.add(construirVehiculoResponse(vehiculoDto)));

        return listaVehiculosResponse;
    }

    @Override
    public void desactivarVehiculo(Integer id) {
        if (!vehiculoIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El vehiculo que has ingresado no existe");
        }

        vehiculoIRepository.desactivar(id);
    }

    @Override
    public void activarVehiculo(Integer id) {

        if (!vehiculoIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "El vehiculo que has ingresado no existe");
        }
        vehiculoIRepository.activar(id);
    }

    @Override
    public List<VehiculoFotoResponse> obtenerVehiculosConFotos() {
        List<VehiculoDto> listaVehiculosDto = vehiculoIRepository.findAll().stream()
                .map(vehiculoMapper::toDto)
                .toList();

        List<VehiculoFotoResponse> listaVehiculosFotoResponse = new ArrayList<>();

        listaVehiculosDto.forEach(vehiculoDto -> listaVehiculosFotoResponse.add(construirVehiculoFotoResponse(vehiculoDto)));

        return listaVehiculosFotoResponse;
    }

    @Override
    public List<VehiculoFotoResponse> obtenerVehiculosDisponibles(Integer idTipoVehiculo, LocalDate fechaInicio, LocalDate fechaEntrega) {

        List<VehiculoDto> listaVehiculosPorTipoDto = vehiculoIRepository.findAllByTipo(idTipoVehiculo)
                .stream()
                .map(vehiculoMapper::toDto)
                .toList();

        listaVehiculosPorTipoDto.stream()
                .filter(vehiculoDto -> vehiculoIRepository.isVehiculoDisponible(vehiculoDto.getId(), fechaInicio, fechaEntrega))
                .toList();

        List<VehiculoFotoResponse> listaVehiculosDisponibles = new ArrayList<>();

        listaVehiculosPorTipoDto.forEach(vehiculoDto -> listaVehiculosDisponibles.add(construirVehiculoFotoResponse(vehiculoDto)));

        return listaVehiculosDisponibles;
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

    private VehiculosDatosResponse construirVehiculoResponse(VehiculoDto vehiculoDto) {
        return VehiculosDatosResponse.builder()
                .id(vehiculoDto.getId())
                .placa(vehiculoDto.getPlaca())
                .modelo(modeloIRepository.findModeloById(vehiculoDto.getIdModelo()))
                .marca(marcaIRepository.findMarcaByModeloId(vehiculoDto.getIdModelo()))
                .anio(vehiculoDto.getAnio())
                .kilometraje(vehiculoDto.getKilometraje())
                .color(vehiculoDto.getColor())
                .activo(vehiculoDto.isActivo())
                .valorDia(vehiculoDto.getValorDia())
                .build();
    }

    private VehiculoFotoResponse construirVehiculoFotoResponse(VehiculoDto vehiculoDto) {
        VehiculosDatosResponse vehiculoInfo = construirVehiculoResponse(vehiculoDto);
        String nombreFoto = vehiculoIRepository.fotoByIdVehiculo(vehiculoDto.getId());
        String extension = archivoUtil.obtenerExtension(nombreFoto);
        byte[] foto;

        try {
            foto = archivoUtil.obtenerArchivo(nombreFoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        VehiculoFotoResponse vehiculoFotoResponse = VehiculoFotoResponse.builder()
                .VehiculoInfo(vehiculoInfo)
                .mediaType("image/" + extension)
                .base64Image(Base64.getEncoder().encodeToString(foto)).build();

        return vehiculoFotoResponse;
    }
}
