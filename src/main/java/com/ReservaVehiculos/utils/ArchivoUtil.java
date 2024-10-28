package com.ReservaVehiculos.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ArchivoUtil {

    private final static String UBICACION = "C:/ReservaVehiculos/archivos/";

    public String subirArchivo(MultipartFile foto) throws IOException {

        String nuevoNombre = UUID.randomUUID().toString() + "." + obtenerExtension(foto.getOriginalFilename());

        File directorio = new File(ArchivoUtil.UBICACION);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File archivoDestino= new File(directorio,nuevoNombre);

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

}
