package com.JJsCarRent.utils.adapters;

import org.springframework.stereotype.Component;

import java.io.IOException;

public interface ImageConverter {
    byte[] convertToWebp(byte[] imagenBytes) throws IOException;
}
