package com.JJsCarRent.utils.strategy;

public abstract class ValidarExtensiones {

    public abstract boolean esExtensionValida(String nombreArchivo);

    public String obtenerExtension(String nombreArchivo) {
        return nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
    }
}
