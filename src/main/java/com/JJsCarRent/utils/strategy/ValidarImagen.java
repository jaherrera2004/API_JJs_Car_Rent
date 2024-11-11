package com.JJsCarRent.utils.strategy;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ValidarImagen extends ValidarExtensiones{

    private static final List<String> EXTENSIONES_VALIDAS = Arrays.asList("jpg", "jpeg", "png", "webp");

    @Override
    public boolean esExtensionValida(String nombreArchivo) {
        return EXTENSIONES_VALIDAS.contains(obtenerExtension(nombreArchivo));
    }
}
