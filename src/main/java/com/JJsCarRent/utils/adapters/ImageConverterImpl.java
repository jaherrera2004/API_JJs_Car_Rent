package com.JJsCarRent.utils.adapters;

import com.luciad.imageio.webp.WebPWriteParam;
import org.springframework.stereotype.Component;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ImageConverterImpl implements ImageConverter{

    @Override
    public byte[] convertToWebp(byte[] imageBytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        BufferedImage bi = ImageIO.read(bais);
        ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
        writer.setOutput(ios);

        ImageWriteParam writeParam = writer.getDefaultWriteParam();
        writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        writeParam.setCompressionType(writeParam.getCompressionTypes()[WebPWriteParam.LOSSLESS_COMPRESSION]);
        writeParam.setCompressionQuality(0.70f);

        writer.write(null, new IIOImage(bi, null, null), writeParam);
        ios.close(); //importante que este antes que baos.to..
        return  baos.toByteArray();
    }
}
