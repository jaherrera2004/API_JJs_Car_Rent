package com.ReservaVehiculos.utils;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class ArchivoUtil {

    private final static String UBICACION = "C:/ReservaVehiculos/archivos/";
    private static final List<String> EXTENSIONES_VALIDAS = Arrays.asList("jpg", "jpeg", "png");
    private final static float TAMANIO_MAXIMO = 2 * 1024 * 1024; // 2MB

    public String subirArchivo(MultipartFile foto) throws IOException {

        String nuevoNombre = UUID.randomUUID().toString() + "." + obtenerExtension(foto.getOriginalFilename());

        File directorio = new File(ArchivoUtil.UBICACION);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File archivoDestino = new File(directorio, nuevoNombre);

        foto.transferTo(archivoDestino);

        return nuevoNombre;
    }

    public byte[] obtenerArchivo() {
        return null;
    }

    private String obtenerExtension(String nombreArchivo) {

        return nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
    }

    public void eliminarArchivo() {

    }

    public boolean esExtensionValida(MultipartFile archivo) {

        String nombreArchivo = archivo.getOriginalFilename();
        if (nombreArchivo != null && nombreArchivo.contains(".")) {
            String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();
            return EXTENSIONES_VALIDAS.contains(extension);
        }
        return false;
    }

    public boolean esTamanioValido(MultipartFile archivo) {

        if (archivo.getSize() > TAMANIO_MAXIMO) {
            return false;
        }
        return true;
    }

}
