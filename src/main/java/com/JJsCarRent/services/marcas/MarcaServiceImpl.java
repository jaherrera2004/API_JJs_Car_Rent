package com.JJsCarRent.services.marcas;

import com.JJsCarRent.utils.adapters.ImageAdapter;
import com.JJsCarRent.utils.mappers.MarcaMapper;
import com.JJsCarRent.models.dto.MarcaDto;
import com.JJsCarRent.models.exceptions.HttpGenericException;
import com.JJsCarRent.models.request.marcas.MarcaLogoRequest;
import com.JJsCarRent.models.request.marcas.MarcaRequest;
import com.JJsCarRent.models.response.marcas.MarcaConLogoResponse;
import com.JJsCarRent.repository.marcas.MarcaIRepository;
import com.JJsCarRent.utils.ArchivoUtil;
import com.JJsCarRent.utils.strategy.TipoArchivo;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaIService {

    private final MarcaMapper marcaMapper;
    private final MarcaIRepository marcaIRepository;
    private final ArchivoUtil archivoUtil;
    private final ImageAdapter imageAdapter;

    @Override
    public void agregarMarca(MarcaRequest request, MultipartFile logo) throws IOException {

        if (marcaIRepository.existsByMarca(request.getMarca())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Esta marca ya existe");
        }

        if(!archivoUtil.esExtensionValida(logo.getOriginalFilename(), TipoArchivo.IMAGEN)){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"Debes enviar una foto en un formato valido");
        }

        if (!archivoUtil.esTamanioValido(logo.getBytes())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "la foto de demasiado grande");
        }

        Pair<byte[], String> logoWebp = archivoUtil.transformarAWebp(logo.getBytes(),logo.getOriginalFilename());

        String nombreFoto= archivoUtil.cambiarNombreArchivo(logoWebp.b);
        archivoUtil.subirArchivo(logoWebp.a, nombreFoto);

        MarcaDto marcaDto = construirMarca(request);
        marcaIRepository.save(marcaMapper.toEntity(marcaDto));
        marcaIRepository.actualizarFoto(nombreFoto, request.getMarca());
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

        if(!archivoUtil.esExtensionValida(request.getLogo().getOriginalFilename(),TipoArchivo.IMAGEN)){
            throw new HttpGenericException(HttpStatus.BAD_REQUEST,"Debes enviar una foto en un formato valido");
        }

        if (!archivoUtil.esTamanioValido(request.getLogo().getBytes())) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "la foto de demasiado grande");
        }

        if (marcaIRepository.tieneLogo(request.getMarca())) {
            archivoUtil.eliminarArchivo(marcaIRepository.obtenerLogoPorMarca(request.getMarca()));
        }
        Pair<byte[], String> logoWebp = archivoUtil.transformarAWebp(request.getLogo().getBytes(),request.getLogo().getOriginalFilename());

        String nombreFoto= archivoUtil.cambiarNombreArchivo(logoWebp.b);
        archivoUtil.subirArchivo(logoWebp.a, nombreFoto);
        marcaIRepository.actualizarFoto(nombreFoto, request.getMarca());
    }

    @Override
    public Pair<ByteArrayResource, String> obtenerLogo(Integer id) throws IOException {

        if (!marcaIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "la marca que ingresaste no existe");
        }

        if (!marcaIRepository.tieneLogo(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "La marca no tiene logo");
        }

        String logoNombre = marcaIRepository.obtenerLogoPorId(id);
        byte[] logoBytes = archivoUtil.obtenerArchivo(logoNombre);
        String extension = archivoUtil.obtenerExtension(logoNombre);
        String mediaType = "image/" + extension;

        return new Pair<>(new ByteArrayResource(logoBytes), mediaType);

    }

    @Override
    public void eliminarLogo(Integer id) throws IOException {

        if (!marcaIRepository.existsById(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "la marca que ingresaste no existe");
        }

        if (!marcaIRepository.tieneLogo(id)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "La marca no tiene logo");
        }

        archivoUtil.eliminarArchivo(marcaIRepository.obtenerLogoPorId(id));
        marcaIRepository.eliminarLogo(id);
    }

    @Override
    public List<MarcaConLogoResponse> obtenerMarcasConLogo() {

        List<MarcaConLogoResponse> listaMarcasConLogo = new ArrayList<>();
        List<MarcaDto> listaMarcaDto = marcaIRepository.findAll().stream()
                .map(marcaMapper::toDto)
                .toList();

        listaMarcaDto.forEach(marcaDto -> {

            String nombreLogo = marcaIRepository.obtenerLogoPorId(marcaDto.getId());
            String extension = archivoUtil.obtenerExtension(nombreLogo);

            byte[] logo;
            try {
                logo = archivoUtil.obtenerArchivo(nombreLogo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MarcaConLogoResponse marcaConLogo = MarcaConLogoResponse.builder()
                    .marcaInfo(marcaMapper.toDto(marcaIRepository.findById(marcaDto.getId())))
                    .mediaType("image/" + extension)
                    .base64Image(Base64.getEncoder().encodeToString(logo))
                    .build();

            listaMarcasConLogo.add(marcaConLogo);
        });

        return listaMarcasConLogo;
    }

    private MarcaDto construirMarca(MarcaRequest request) {
        return MarcaDto.builder()
                .marca(request.getMarca())
                .activo(true)
                .build();
    }
}
