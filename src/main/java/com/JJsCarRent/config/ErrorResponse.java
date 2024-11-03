package com.JJsCarRent.config;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HttpStatus estado;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer codigo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ruta;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime marcaDeTiempo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mensaje;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fechaInicial;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fechaFin;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ValidacionError> errores;

}
