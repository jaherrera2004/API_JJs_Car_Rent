package com.JJsCarRent.models.response.marcas;

import com.JJsCarRent.models.dto.MarcaDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class MarcaConLogoResponse {

    private MarcaDto marcaInfo;
    private String base64Image;
    private String mediaType;

}
