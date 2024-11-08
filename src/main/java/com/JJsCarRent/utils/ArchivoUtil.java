package com.JJsCarRent.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class ArchivoUtil {

    private final static String UBICACION = "C:/ReservaVehiculos/archivos/";
    private static final List<String> EXTENSIONES_VALIDAS = Arrays.asList("jpg", "jpeg", "png", "webp");
    private final static float TAMANIO_MAXIMO = 2 * 1024 * 1024; // 2MB

    private String cambiarNombreArchivo(String nombreOriginal){
        return UUID.randomUUID().toString() + "." + obtenerExtension(nombreOriginal);
    }

    public String subirArchivo(byte[] contenido, String nombreOriginal) throws IOException {

        String nuevoNombre = cambiarNombreArchivo(nombreOriginal);

        File directorio = new File(ArchivoUtil.UBICACION);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File archivoDestino = new File(directorio, nuevoNombre);

        try (FileOutputStream fos = new FileOutputStream(archivoDestino)) {
            fos.write(contenido);
        }

        return nuevoNombre;
    }

    public byte[] obtenerArchivo(String nombreArchivo) throws IOException {
        File archivo = new File(ArchivoUtil.UBICACION, nombreArchivo);

        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo no fue encontrado: " + nombreArchivo);
        }
        return Files.readAllBytes(archivo.toPath());
    }

    public String obtenerExtension(String nombreArchivo) {
        return nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
    }

    public void eliminarLogo(String nombreLogo) throws IOException {

        File logo = new File(ArchivoUtil.UBICACION, nombreLogo);

        if (!logo.exists()) {
            throw new FileNotFoundException("Archivo no encontrado");
        }

        logo.delete();
    }

    public boolean esExtensionValida(String nombreArchivo) {
        if (nombreArchivo != null && nombreArchivo.contains(".")) {
            String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();
            return EXTENSIONES_VALIDAS.contains(extension);
        }
        return false;
    }

    public boolean esTamanioValido(byte[] contenido) {
        return contenido.length <= TAMANIO_MAXIMO;
    }

}
