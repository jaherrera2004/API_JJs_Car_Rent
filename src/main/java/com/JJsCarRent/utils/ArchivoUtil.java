package com.JJsCarRent.utils;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
    
    public String subirArchivo(MultipartFile foto) throws IOException {

        String nuevoNombre = cambiarNombreArchivo(foto.getOriginalFilename());

        File directorio = new File(ArchivoUtil.UBICACION);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File archivoDestino = new File(directorio, nuevoNombre);

        foto.transferTo(archivoDestino);

        return nuevoNombre;
    }

    public byte[] obtenerArchivo(String nombreArchivo) throws IOException {
        File foto = new File(ArchivoUtil.UBICACION, nombreArchivo);

        if (!foto.exists()) {
            throw new FileNotFoundException("El archivo no fue encontrado" + nombreArchivo);
        }
        return Files.readAllBytes(foto.toPath());
    }

    public String obtenerExtension(String nombreArchivo) {

        return nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
    }

    public void eliminarLogo(String nombreLogo) throws IOException{

        File logo = new File(ArchivoUtil.UBICACION,nombreLogo);

        if(!logo.exists()){
            throw new FileNotFoundException("Archivo no encontrado");
        }

        logo.delete();
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

        if (archivo.getSize() > ArchivoUtil.TAMANIO_MAXIMO) {
            return false;
        }
        return true;
    }

}
