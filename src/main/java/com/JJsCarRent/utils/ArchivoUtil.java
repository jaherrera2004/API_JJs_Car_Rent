package com.JJsCarRent.utils;

import com.JJsCarRent.utils.adapters.ImageAdapter;
import com.JJsCarRent.utils.strategy.TipoArchivo;
import com.JJsCarRent.utils.strategy.ValidarExtensiones;
import com.JJsCarRent.utils.strategy.ValidarImagen;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ArchivoUtil {

    private final static String UBICACION = "C:/ReservaVehiculos/archivos/";
    private static final List<String> EXTENSIONES_VALIDAS = Arrays.asList("jpg", "jpeg", "png", "webp");
    private final static float TAMANIO_MAXIMO = 2 * 1024 * 1024; // 2MB

    private final ImageAdapter imageAdapter;

    private String cambiarNombreArchivo(String nombreOriginal) {
        return UUID.randomUUID().toString() + "." + obtenerExtension(nombreOriginal);
    }

    public String subirArchivo(byte[] contenido, String nombre) throws IOException {

        String nuevoNombre = cambiarNombreArchivo(nombre);

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

    public void eliminarArchivo(String nombreArchivo) throws IOException {

        File logo = new File(ArchivoUtil.UBICACION, nombreArchivo);

        if (!logo.exists()) {
            throw new FileNotFoundException("Archivo no encontrado");
        }

        logo.delete();
    }

    public boolean esExtensionValida(String nombreArchivo, TipoArchivo tipoArchivo) {
        switch (tipoArchivo) {
            case IMAGEN:
                ValidarExtensiones validador = new ValidarImagen();
                return validador.esExtensionValida(nombreArchivo);
        }
        return false;
    }

    public boolean esTamanioValido(byte[] contenido) {
        return contenido.length <= TAMANIO_MAXIMO;
    }

    public Pair<byte[], String> transformarAWebp(byte[] contenido, String nombre) throws IOException {
        byte[] logoWebp = imageAdapter.getWebpImage(contenido);
        String nombreLogoWebp = nombre.substring(0, nombre.lastIndexOf(".")) + ".webp";
        return new Pair<>(logoWebp, nombreLogoWebp);
    }

}
