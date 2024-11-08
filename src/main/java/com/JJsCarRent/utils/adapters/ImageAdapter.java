package com.JJsCarRent.utils.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ImageAdapter {
    private final ImageConverter converter;

    public byte[] getWebpImage(byte[] imageBytes) throws IOException {
        return converter.convertToWebp(imageBytes);
    }
}
